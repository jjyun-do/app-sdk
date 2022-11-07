package healthstack.app.status

import healthstack.app.R
import healthstack.healthdata.link.HealthDataLinkHolder
import java.time.Duration
import java.time.Instant
import java.time.temporal.ChronoUnit.DAYS
import kotlin.math.roundToInt

object SleepSessionStatus : StatusDataType() {
    override fun getIcon(): Int = R.drawable.ic_sleep

    override fun getUnitString(): String = " hrs\nSleep"

    override suspend fun getLatestStatus(): Any? = getLatestSleepSession()

    private suspend fun getLatestSleepSession(): Any? {
        val yesterday = Instant.now().truncatedTo(DAYS).minus(1, DAYS)
        val healthData = HealthDataLinkHolder.getInstance().getHealthData(
            yesterday,
            Instant.now(),
            "SleepSession"
        )

        if (healthData.data.isEmpty()) return null
        val latestSleep = healthData.data.last()
        val startTime = latestSleep["startTime"] as String
        val endTime = latestSleep["endTime"] as String
        val sleepDuration = Duration.between(
            Instant.parse(startTime),
            Instant.parse(endTime)
        ).toMinutes() / 60.0

        return (sleepDuration * 10).roundToInt() / 10.0
    }
}
