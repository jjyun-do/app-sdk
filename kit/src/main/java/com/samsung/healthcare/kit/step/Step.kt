package com.samsung.healthcare.kit.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.Model
import com.samsung.healthcare.kit.view.View

abstract class Step<T : Model, R>(
    val id: String,
    val name: String,
    val model: T,
    val view: View<T>,
    val getResult: () -> R,
) {
    @Composable
    abstract fun Render(callbackCollection: CallbackCollection): Unit

    fun getState(): T = model

    // NOTE: We can add a special logic to get the result of Step
    var result: R = getResult()
}
