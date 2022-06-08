package com.samsung.healthcare.kit.external.network

import com.samsung.healthcare.kit.external.background.SyncHealthDataClient
import com.samsung.healthcare.kit.external.data.HealthData
import com.samsung.healthcare.kit.external.data.HealthDataId
import retrofit2.Call

class ResearchPlatformAdapter(
    private val networkClient: ResearchPlatformNetworkClient,
    private val projectId: String,
) : SyncHealthDataClient {

    override fun syncHealthData(healthData: HealthData): Call<List<HealthDataId>> =
        networkClient.syncHealthData(projectId, healthData)
}
