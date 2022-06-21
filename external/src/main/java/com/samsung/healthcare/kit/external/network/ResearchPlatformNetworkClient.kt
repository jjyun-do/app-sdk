package com.samsung.healthcare.kit.external.network

import com.samsung.healthcare.kit.external.data.HealthData
import com.samsung.healthcare.kit.external.data.HealthDataId
import com.samsung.healthcare.kit.external.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ResearchPlatformNetworkClient {
    @POST("/api/projects/{projectId}/healthData")
    fun syncHealthData(
        @Path("projectId") projectId: String,
        @Body healthData: HealthData,
    ): Call<List<HealthDataId>>

    @POST("/api/projects/{projectId}/users")
    suspend fun registerUser(
        @Header("id_token") idToken: String,
        @Path("projectId") projectId: String,
        @Body user: User,
    )
}
