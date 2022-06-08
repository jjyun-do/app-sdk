package com.samsung.healthcare.kit.external.background

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.samsung.healthcare.kit.external.background.SyncManager.Companion.HEALTH_DATA_TYPE_KEY
import com.samsung.healthcare.kit.external.data.HealthDataId
import com.samsung.healthcare.kit.external.source.HealthPlatformManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val healthPlatformManager: HealthPlatformManager,
    private val syncHealthDataClient: SyncHealthDataClient,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        val healthDataTypeString: String =
            inputData.getString(HEALTH_DATA_TYPE_KEY) ?: return Result.failure()

        healthDataTypeString.let {
            val healthDataToSync = healthPlatformManager.getHealthData(it)
            // TODO: Authenticate to Research Platform
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

            return Result.success()
        }
    }
}
