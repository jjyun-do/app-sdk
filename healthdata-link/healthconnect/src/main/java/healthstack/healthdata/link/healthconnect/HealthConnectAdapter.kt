package healthstack.healthdata.link.healthconnect

import healthstack.healthdata.link.HealthData
import healthstack.healthdata.link.HealthDataLink
import java.time.Instant

class HealthConnectAdapter : HealthDataLink {
    override suspend fun hasAllPermissions(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun requestPermissions() {
        TODO("Not yet implemented")
    }

    override suspend fun getHealthData(startTime: Instant, endTime: Instant, healthDataTypeName: String): HealthData {
        TODO("Not yet implemented")
    }

    override fun isIntervalData(healthDataName: String): Boolean {
        TODO("Not yet implemented")
    }
}
