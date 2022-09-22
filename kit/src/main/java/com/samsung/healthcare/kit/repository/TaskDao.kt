package com.samsung.healthcare.kit.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.samsung.healthcare.kit.entity.Task

// TODO: use Flow to avoid blocking call
@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    @Insert
    fun insertAll(tasks: List<Task>)
}
