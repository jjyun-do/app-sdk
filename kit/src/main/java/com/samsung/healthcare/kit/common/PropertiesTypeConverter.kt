package com.samsung.healthcare.kit.common

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.samsung.healthcare.kit.entity.Task.Properties

@ProvidedTypeConverter
class PropertiesTypeConverter(private val gson: Gson) {

    @TypeConverter
    fun objectToJson(value: Properties): String = gson.toJson(value)

    @TypeConverter
    fun jsonToObject(value: String): Properties = gson.fromJson(value, Properties::class.java)
}
