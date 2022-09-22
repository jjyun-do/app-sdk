package com.samsung.healthcare.kit.external.network

import com.samsung.healthcare.kit.external.data.TaskSpec
import java.time.LocalDateTime

interface TaskClient {
    suspend fun getTasks(idToken: String, startTime: LocalDateTime, endTime: LocalDateTime): List<TaskSpec>
}
