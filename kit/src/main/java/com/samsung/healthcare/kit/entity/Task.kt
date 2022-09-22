package com.samsung.healthcare.kit.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samsung.healthcare.kit.external.data.Item
import java.time.LocalDateTime

@Entity
data class Task(
    @PrimaryKey
    val id: Int? = null,
    val revisionId: Int,
    val taskId: String,
    val properties: Properties,
    val result: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val scheduledAt: LocalDateTime? = null,
    val validUntil: LocalDateTime? = null,
    val submittedAt: LocalDateTime? = null,
) {
    data class Properties(
        val title: String,
        val description: String,
        val items: List<Item>,
    )
}
