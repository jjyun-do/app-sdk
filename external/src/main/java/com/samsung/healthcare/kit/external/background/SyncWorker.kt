package com.samsung.healthcare.kit.external.background

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.samsung.healthcare.kit.external.background.SyncManager.Companion.HEALTH_DATA_TYPE_KEY
import com.samsung.healthcare.kit.external.data.HealthData
import com.samsung.healthcare.kit.external.data.HealthData.Companion.END_TIME_KEY
import com.samsung.healthcare.kit.external.data.HealthDataId
import com.samsung.healthcare.kit.external.datastore.MetaDataStore
import com.samsung.healthcare.kit.external.source.HealthPlatformAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.Instant

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val healthPlatformAdapter: HealthPlatformAdapter,
    private val syncHealthDataClient: SyncHealthDataClient,
    private val metaDataStore: MetaDataStore,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        val healthDataTypeString: String =
            inputData.getString(HEALTH_DATA_TYPE_KEY) ?: return Result.failure()

        val startTime: String = metaDataStore.readLatestSyncTime(healthDataTypeString)
        val endTime: String = Instant.now().toString()

        val healthDataToSync =
            healthPlatformAdapter.getHealthData(startTime, endTime, healthDataTypeString)

        if (healthDataToSync.data.isNotEmpty()) {
            // TODO: Authenticate to Research Platform
            sync(healthDataToSync)

            val latestSyncTime = if (HealthPlatformAdapter.isInterval(healthDataTypeString))
                healthDataToSync.data.last()[END_TIME_KEY].toString()
            else
                endTime

            metaDataStore.saveLatestSyncTime(healthDataTypeString, latestSyncTime)
        }

        return Result.success()
    }

    private fun sync(healthDataToSync: HealthData) {
        syncHealthDataClient.syncHealthData(healthDataToSync)
            .enqueue(
                object : Callback<List<HealthDataId>> {
                    override fun onResponse(
                        call: Call<List<HealthDataId>>,
                        response: Response<List<HealthDataId>>,
                    ) {
                        if (response.isSuccessful)
                            TODO("Not yet implemented")
                        else
                            TODO("Not yet implemented")
                    }

                    override fun onFailure(
                        call: Call<List<HealthDataId>>,
                        t: Throwable,
                    ) {
                        TODO("Not yet implemented")
                    }
                }
            )
    }
}
