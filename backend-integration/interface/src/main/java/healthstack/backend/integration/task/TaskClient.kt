package healthstack.backend.integration.task

import java.time.LocalDateTime

interface TaskClient {
    suspend fun getTasks(idToken: String, lastSyncTime: LocalDateTime): List<TaskSpec>

    suspend fun uploadResult(idToken: String, result: TaskResult)
}
