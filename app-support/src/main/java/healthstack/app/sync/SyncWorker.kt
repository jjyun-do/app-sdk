package healthstack.app.sync

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.firebase.auth.FirebaseAuth
import healthstack.app.pref.MetaDataStore
import healthstack.app.sync.SyncManager.Companion.HEALTH_DATA_TYPE_KEY
import healthstack.backend.integration.BackendFacade
import healthstack.backend.integration.BackendFacadeHolder
import healthstack.healthdata.link.HealthData
import healthstack.healthdata.link.HealthData.Companion.END_TIME_KEY
import healthstack.healthdata.link.HealthDataLink
import healthstack.healthdata.link.HealthDataLinkHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant

class SyncWorker constructor(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {
    private val healthDataSyncClient: BackendFacade = BackendFacadeHolder.getInstance()
    private val healthDataLink: HealthDataLink = HealthDataLinkHolder.getInstance()

    override suspend fun doWork(): Result {
        val healthDataTypeString: String =
            inputData.getString(HEALTH_DATA_TYPE_KEY) ?: return Result.failure()

        val metaDataStore = MetaDataStore(applicationContext)

        val startTime = Instant.parse(metaDataStore.readLatestSyncTime(healthDataTypeString))
        val endTime = Instant.now()

        val healthDataToSync = healthDataLink
            .getHealthData(startTime, endTime, healthDataTypeString)
            .let { healthData ->
                HealthData(
                    healthData.type,
                    healthData.data.filter {
                        !it.containsKey(END_TIME_KEY) ||
                            endTime >= Instant.parse(it[END_TIME_KEY] as String)
                    }
                )
            }

        if (healthDataToSync.data.isNotEmpty()) {
            FirebaseAuth.getInstance().currentUser?.getIdToken(false)
                ?.addOnSuccessListener { result ->
                    result.token?.let { idToken ->
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                sync(idToken, healthDataToSync)

                                val latestSyncTime = if (healthDataLink.isIntervalData(healthDataTypeString))
                                    healthDataToSync.data.last()[END_TIME_KEY].toString()
                                else
                                    endTime.toString()

                                metaDataStore.saveLatestSyncTime(healthDataTypeString, latestSyncTime)
                            } catch (e: Exception) {
                                Log.d(SyncWorker::class.simpleName, "fail to sync health data")
                                e.printStackTrace()
                            }
                        }
                    }
                }?.addOnFailureListener {
                    Log.d(SyncWorker::class.simpleName, "fail to get id token")
                }
        } else {
            Log.d(SyncWorker::class.simpleName, "nothing to sync")
        }

        return Result.success()
    }

    private suspend fun sync(idToken: String, healthDataToSync: HealthData) {
        healthDataSyncClient.sync(idToken, healthDataToSync)
    }
}
