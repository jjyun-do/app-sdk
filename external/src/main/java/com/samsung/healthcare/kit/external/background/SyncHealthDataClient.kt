package com.samsung.healthcare.kit.external.background

import com.samsung.healthcare.kit.external.data.HealthData
import com.samsung.healthcare.kit.external.data.HealthDataId
import retrofit2.Call

interface SyncHealthDataClient {
    fun syncHealthData(healthData: HealthData): Call<List<HealthDataId>>
}
