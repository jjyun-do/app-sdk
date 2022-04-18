package com.samsung.healthcare.kit.task

import androidx.compose.runtime.Composable

abstract class Task(
    val id: String,
    val name: String,
    val description: String,
    val callback: () -> Unit,
) {
    @Composable
    abstract fun Render(): Unit
}
