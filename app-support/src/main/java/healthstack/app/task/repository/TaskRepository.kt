package healthstack.app.task.repository

import healthstack.kit.task.base.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import healthstack.app.task.entity.Task as TaskEntity

interface TaskRepository {
    fun getActiveDailyTask(targetTime: LocalDateTime): Flow<List<Task>>

    fun getUpcomingDailyTask(targetTime: LocalDateTime): Flow<List<Task>>

    fun getCompletedDailyTasks(targetDay: LocalDate): Flow<List<Task>>

    suspend fun insertAll(tasks: List<TaskEntity>)

    suspend fun updateResult(task: Task)
}
