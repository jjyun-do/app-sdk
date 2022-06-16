package com.samsung.healthcare.kit.sample

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.google.android.libraries.healthdata.HealthDataService
import com.samsung.healthcare.kit.external.background.SyncManager
import com.samsung.healthcare.kit.external.network.ResearchPlatformAdapter
import com.samsung.healthcare.kit.external.source.HealthPlatformAdapter
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ResearchApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var syncManager: SyncManager

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()

        val requiredHealthData = listOf("HeartRate", "Steps", "SleepSession")
        HealthPlatformAdapter.initialize(HealthDataService.getClient(this), requiredHealthData)
        ResearchPlatformAdapter.initialize(this)
        // add logics to decide if background worker can be started or not
        syncManager.startBackgroundSync()
    }
}
