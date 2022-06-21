package com.samsung.healthcare.kit.external.network

import android.content.Context
import com.samsung.healthcare.kit.external.R
import com.samsung.healthcare.kit.external.background.SyncHealthDataClient
import com.samsung.healthcare.kit.external.data.HealthData
import com.samsung.healthcare.kit.external.data.HealthDataId
import com.samsung.healthcare.kit.external.data.User
import com.samsung.healthcare.kit.external.network.util.RetrofitFactory
import retrofit2.Call

class ResearchPlatformAdapter private constructor(
    private val networkClient: ResearchPlatformNetworkClient,
    private val projectId: String,
) : SyncHealthDataClient, UserRegistrationClient {

    override fun syncHealthData(healthData: HealthData): Call<List<HealthDataId>> =
        networkClient.syncHealthData(projectId, healthData)

    override suspend fun registerUser(idToken: String, user: User) {
        networkClient.registerUser(idToken, projectId, user)
    }

    companion object {
        private lateinit var INSTANCE: ResearchPlatformAdapter

        fun initialize(context: Context) {
            synchronized(this) {
                if (::INSTANCE.isInitialized.not()) {
                    INSTANCE = ResearchPlatformAdapter(
                        RetrofitFactory.create(
                            context.getString(R.string.research_platform_address),
                            ResearchPlatformNetworkClient::class.java
                        ),
                        context.getString(R.string.project_Id)
                    )
                }
            }
        }

        fun getInstance(): ResearchPlatformAdapter = INSTANCE
    }
}
