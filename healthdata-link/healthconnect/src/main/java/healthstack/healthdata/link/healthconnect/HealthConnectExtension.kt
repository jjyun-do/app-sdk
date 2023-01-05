package healthstack.healthdata.link.healthconnect

import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.Record
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.response.ReadRecordsResponse
import healthstack.healthdata.link.HealthData

fun ReadRecordsResponse<out Record>.toHealthData(healthDataTypeName: String): HealthData {
    // TODO: refactor logic..
    val healthDataSet = mutableListOf<Map<String, Any>>()

    records.forEach { record ->
        when (record) {
            is HeartRateRecord ->
                record.samples.forEach {
                    healthDataSet.add(
                        mapOf(
                            "bpm" to it.beatsPerMinute,
                            HealthData.TIME_KEY to it.time
                        )
                    )
                }
            is StepsRecord ->
                healthDataSet.add(
                    mapOf(
                        "count" to record.count,
                        HealthData.START_TIME_KEY to record.startTime,
                        HealthData.END_TIME_KEY to record.endTime
                    )
                )
            else -> throw RuntimeException("Unsupported Data Type.")
        }
    }

    return HealthData(healthDataTypeName, healthDataSet)
}
