package healthstack.app.pref

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.time.temporal.ChronoUnit.DAYS
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MetaDataStoreTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun testMetaDataUpdate() {
        val metaDataStore = MetaDataStore(context)
        val today = Instant.now().truncatedTo(DAYS)
        val heartRate = "HeartRate"
        runTest {
            val initSyncTime = metaDataStore.readLatestSyncTime(heartRate)
            assertEquals(today.toString(), initSyncTime)

            val updateSyncTime = today.plus(3L, ChronoUnit.HOURS)
            metaDataStore.saveLatestSyncTime(heartRate, updateSyncTime.toString())
            val lastSyncTime = metaDataStore.readLatestSyncTime(heartRate)
            assertEquals(updateSyncTime.toString(), lastSyncTime)

            metaDataStore.clearDataStore()
            assertEquals(today.toString(), metaDataStore.readLatestSyncTime(heartRate))
        }
    }
}
