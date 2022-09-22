package com.samsung.healthcare.kit.external.data

import com.google.gson.annotations.SerializedName

data class TaskSpec(
    val revisionId: Int,
    @SerializedName("id")
    val taskId: String,
    val title: String,
    val description: String,
    val schedule: String,
    val startTime: String,
    val endTime: String,
    val validTime: Long,
    val items: List<Item>,
)

data class Item(
    val name: String,
    val type: String,
    val contents: Contents,
)

data class Contents(
    val type: String,
    val title: String,
    val explanation: String? = null,
    val properties: Properties,
    val sequence: Int,
)

data class Properties(
    val tag: String,
    val options: List<Option>,
)

data class Option(
    val value: String,
)
