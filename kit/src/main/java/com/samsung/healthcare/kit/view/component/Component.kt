package com.samsung.healthcare.kit.view.component

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.Model

abstract class Component<T : Model> {
    @Composable
    abstract fun Render(
        model: T,
        callbackCollection: CallbackCollection,
    ): Unit
}
