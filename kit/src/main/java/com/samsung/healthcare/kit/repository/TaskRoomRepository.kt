package com.samsung.healthcare.kit.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.samsung.healthcare.kit.common.LocalDateTimeConverter
import com.samsung.healthcare.kit.common.PropertiesTypeConverter
import com.samsung.healthcare.kit.entity.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(
    value = [
        PropertiesTypeConverter::class,
        LocalDateTimeConverter::class
    ]
)
abstract class TaskRoomRepository : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
