package com.samsung.healthcare.kit.external.network

import com.samsung.healthcare.kit.external.data.HealthData
import com.samsung.healthcare.kit.external.data.HealthDataId
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ResearchPlatformNetworkClient {
    @POST("/api/projects/{projectId}/healthData")
    fun syncHealthData(
        @Path("projectId") projectId: String,
        @Body healthData: HealthData,
    ): Call<List<HealthDataId>>
}
