package com.samsung.healthcare.kit.external.background

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.samsung.healthcare.kit.external.background.SyncManager.Companion.HEALTH_DATA_TYPE_KEY
import com.samsung.healthcare.kit.external.source.HealthPlatformManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val healthPlatformManager: HealthPlatformManager,
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {

        val healthDataTypeString: String =
            inputData.getString(HEALTH_DATA_TYPE_KEY) ?: return Result.failure()

        healthDataTypeString.let {
            val dataToSync = healthPlatformManager.readData(it)
            // TODO: Authenticate to Research Platform
            // TODO: Execute data sync
            return Result.success()
        }
    }
}