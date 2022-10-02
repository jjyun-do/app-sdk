package com.samsung.healthcare.kit.repository

import com.samsung.healthcare.kit.task.Task
import com.samsung.healthcare.kit.entity.Task as TaskEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface TaskRepository {
    fun getActiveDailyTask(targetTime: LocalDateTime): Flow<List<Task>>

    fun getUpcomingDailyTask(targetTime: LocalDateTime): Flow<List<Task>>

    fun getCompletedDailyTasks(targetDay: LocalDate): Flow<List<Task>>

    suspend fun insertAll(tasks: List<TaskEntity>)

    suspend fun updateResult(task: Task)
}
