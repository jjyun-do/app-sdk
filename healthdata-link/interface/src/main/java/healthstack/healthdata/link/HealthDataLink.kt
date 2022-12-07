package healthstack.healthdata.link

import java.time.Instant

interface HealthDataLink {
    suspend fun hasAllPermissions(): Boolean

    suspend fun requestPermissions()

    suspend fun getHealthData(
        startTime: Instant,
        endTime: Instant,
        healthDataTypeName: String,
    ): HealthData

    fun isIntervalData(healthDataName: String): Boolean
}
