package com.samsung.healthcare.kit.view

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.Model
import com.samsung.healthcare.kit.step.sub.SubStepHolder

abstract class View<T : Model> {
    @Composable
    abstract fun Render(
        model: T,
        callbackCollection: CallbackCollection,
        holder: SubStepHolder?,
    ): Unit
}
