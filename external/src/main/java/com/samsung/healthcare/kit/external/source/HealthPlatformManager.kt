package com.samsung.healthcare.kit.external.source

import android.content.Context
import androidx.concurrent.futures.await
import com.google.android.libraries.healthdata.HealthDataService
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
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

class HealthPlatformManager(
    context: Context,
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
    }

    private val healthDataClient = HealthDataService.getClient(context)

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

    suspend fun readData(healthDataTypeString: String): String {
        val healthDataType = convertStringToHealthDataType(healthDataTypeString)
        val permissionSet = setOf(Permission.create(healthDataType, AccessType.READ))

        if (!hasPermissions(permissionSet))
            throw IllegalStateException("Required permissions are not granted.")

        val request = ReadDataRequest.builder().also {
            it.setTimeSpec(
                // TODO: get date from research platform or device DB
                TimeSpec.builder()
                    .setStartTime((Instant.now().minus(1, ChronoUnit.DAYS)))
                    .setEndTime(Instant.now())
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

        // TODO: change return type from String to List<HealthDataType>
        val response = healthDataClient.readData(request).await().let {
            if (healthDataType is SampleDataType)
                it.sampleDataSets
            else
                it.intervalDataSets
        }[0].toString()

        return response
    }

    data class HealthDataSyncSpec(
        val healthDataTypeString: String,
        val syncInterval: Long,
        val syncTimeUnit: TimeUnit,
    ) {
        val healthDataType: DataType = convertStringToHealthDataType(healthDataTypeString)
    }
}
