package com.samsung.healthcare.kit.external.source

import androidx.concurrent.futures.await
import com.google.android.libraries.healthdata.HealthDataClient
import com.google.android.libraries.healthdata.data.DataType
import com.google.android.libraries.healthdata.data.IntervalDataType
import com.google.android.libraries.healthdata.data.IntervalDataTypes
import com.google.android.libraries.healthdata.data.IntervalReadSpec
import com.google.android.libraries.healthdata.data.ReadDataRequest
import com.google.android.libraries.healthdata.data.SampleDataType
import com.google.android.libraries.healthdata.data.SampleDataTypes
import com.google.android.libraries.healthdata.data.SampleReadSpec
import com.google.android.libraries.healthdata.data.TimeSpec
import com.google.android.libraries.healthdata.permission.AccessType
import com.google.android.libraries.healthdata.permission.Permission
import com.samsung.healthcare.kit.external.data.HealthData
import java.time.Instant
import java.util.concurrent.TimeUnit

class HealthPlatformAdapter(
    private val healthDataClient: HealthDataClient,
    syncSpecs: List<HealthDataSyncSpec>,
) {
    companion object {
        private val allSampleDataTypeStrings: Set<String> =
            SampleDataTypes.getAllDataTypes().map {
                it.name
            }.toHashSet()

        private val allIntervalDataTypeStrings: Set<String> =
            IntervalDataTypes.getAllDataTypes().map {
                it.name
            }.toHashSet()

        fun convertStringToHealthDataType(healthDataTypeString: String) =
            when (healthDataTypeString) {
                in allSampleDataTypeStrings -> SampleDataTypes.fromName(healthDataTypeString)
                in allIntervalDataTypeStrings -> IntervalDataTypes.fromName(healthDataTypeString)
                else -> throw IllegalArgumentException("Cannot find dataType with given string.")
            }

        fun isInterval(healthDataTypeString: String): Boolean =
            healthDataTypeString in allIntervalDataTypeStrings
    }

    private val healthDataTypes: List<DataType> = syncSpecs.map {
        it.healthDataType
    }

    private val requiredPermissions: Set<Permission> = healthDataTypes.flatMap {
        listOf(
            Permission.create(it, AccessType.READ),
            Permission.create(it, AccessType.WRITE)
        )
    }.toHashSet()

    suspend fun hasPermissions(permissions: Set<Permission>): Boolean {
        val grantedPermissions = healthDataClient.getGrantedPermissions(permissions).await()
        return grantedPermissions.containsAll(permissions)
    }

    suspend fun hasAllPermissions(): Boolean {
        val grantedPermissions = healthDataClient.getGrantedPermissions(requiredPermissions).await()
        return grantedPermissions.containsAll(requiredPermissions)
    }

    suspend fun requestPermissions() {
        if (hasAllPermissions())
            return

        healthDataClient.requestPermissions(requiredPermissions).await()
    }

    suspend fun getHealthData(
        startTime: String,
        endTime: String,
        healthDataTypeString: String,
    ): HealthData {
        val healthDataType = convertStringToHealthDataType(healthDataTypeString)
        val permissionSet = hashSetOf(Permission.create(healthDataType, AccessType.READ))

        if (!hasPermissions(permissionSet))
            throw IllegalStateException("Required permissions are not granted.")

        val request = ReadDataRequest.builder().also {
            it.setTimeSpec(
                // TODO: get date from device DB
                TimeSpec.builder()
                    .setStartTime((Instant.parse(startTime)))
                    .setEndTime(Instant.parse(endTime))
                    .build()
            )
            when (healthDataType) {
                is SampleDataType ->
                    it.addSampleReadSpec(
                        SampleReadSpec.builder(healthDataType).build()
                    )

                is IntervalDataType ->
                    it.addIntervalReadSpec(
                        IntervalReadSpec.builder(healthDataType).build()
                    )

                else -> throw IllegalArgumentException("Given dataType is not supported.")
            }
        }.build()

        return healthDataClient.readData(request).await().toHealthData(healthDataType)
    }

    data class HealthDataSyncSpec(
        val healthDataTypeString: String,
        val syncInterval: Long,
        val syncTimeUnit: TimeUnit,
    ) {
        val healthDataType: DataType = convertStringToHealthDataType(healthDataTypeString)
    }
}