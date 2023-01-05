package healthstack.healthdata.link.healthconnect

import androidx.activity.ComponentActivity
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.PermissionController
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.Record
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter.Companion
import healthstack.healthdata.link.HealthData
import healthstack.healthdata.link.HealthDataLink
import java.time.Instant
import kotlin.reflect.KClass

class HealthConnectAdapter(
    healthDataTypeNames: List<String>,
    private val appCompatActivity: ComponentActivity,
    private val healthConnectClient: HealthConnectClient,
) : HealthDataLink {
    private val healthDataTypes: List<KClass<out Record>> = healthDataTypeNames.map {
        HealthConnectUtils.nameToRecord(it)
    }

    private val requiredPermissions: Set<HealthPermission> = healthDataTypes.map {
        listOf(
            HealthPermission.createReadPermission(it),
            HealthPermission.createWritePermission(it)
        )
    }
        .flatten()
        .toSet()

    private val launcher = appCompatActivity.registerForActivityResult(
        PermissionController.createRequestPermissionResultContract()
    ) { granted ->
        if (granted.containsAll(requiredPermissions)) return@registerForActivityResult
    }

    override suspend fun hasAllPermissions(): Boolean =
        requiredPermissions == healthConnectClient.permissionController.getGrantedPermissions(
            requiredPermissions
        )

    override suspend fun requestPermissions() = launcher.launch(requiredPermissions)

    override suspend fun getHealthData(startTime: Instant, endTime: Instant, healthDataTypeName: String): HealthData {
        // TODO: check permission

        val recordType = HealthConnectUtils.nameToRecord(healthDataTypeName)

        val recordsResponse = healthConnectClient.readRecords(
            ReadRecordsRequest(
                recordType,
                Companion.between(startTime, endTime)
            )
        )

        return recordsResponse.toHealthData(healthDataTypeName)
    }

    override fun isIntervalData(healthDataName: String): Boolean {
        TODO("Not yet implemented")
    }
}
