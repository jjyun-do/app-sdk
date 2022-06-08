package com.samsung.healthcare.kit.external.source

import com.google.android.libraries.healthdata.data.DataType
import com.google.android.libraries.healthdata.data.DoubleField
import com.google.android.libraries.healthdata.data.EnumField
import com.google.android.libraries.healthdata.data.IntervalData
import com.google.android.libraries.healthdata.data.LongField
import com.google.android.libraries.healthdata.data.ReadDataResponse
import com.google.android.libraries.healthdata.data.SampleData
import com.google.android.libraries.healthdata.data.SampleDataType
import com.google.android.libraries.healthdata.data.StringField
import com.samsung.healthcare.kit.external.data.HealthData

fun ReadDataResponse.toHealthData(healthDataType: DataType): HealthData {
    val allFields = healthDataType.allFields

    val dataSet = if (healthDataType is SampleDataType)
        sampleDataSets
    else
        intervalDataSets

    val healthDataSet = dataSet[0].data.map { data ->
        allFields.associateTo(mutableMapOf()) {
            it.name to when (it) {
                is LongField -> data.getLongValue(it)
                is DoubleField -> data.getDoubleValue(it)
                is EnumField -> data.getEnumValue(it)
                is StringField -> data.getStringValue(it)
                else -> Unit
            }
        }.also {
            when (healthDataType) {
                is SampleDataType -> it[HealthData.TIME_KEY] = (data as SampleData).time.toString()
                else -> {
                    it[HealthData.START_TIME_KEY] = (data as IntervalData).startTime.toString()
                    it[HealthData.END_TIME_KEY] = data.endTime.toString()
                }
            }
        }
    }

    return HealthData(healthDataType.name, healthDataSet)
}
