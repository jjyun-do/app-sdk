package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable

abstract class Step(
    val id: String
) {
    abstract val composable: @Composable () -> Unit
}
