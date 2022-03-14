package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable

abstract class Step<R>(
    val id: String,
    val onComplete: (R) -> Unit
) {
    abstract val composable: @Composable () -> Unit

    abstract fun getResult(): R

    internal fun completed() {
        onComplete(getResult())
    }
}
