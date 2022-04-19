package com.samsung.healthcare.kit.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.ConsentTextModel
import com.samsung.healthcare.kit.view.ConsentTextView

class ConsentTextStep(
    id: String,
    name: String,
    model: ConsentTextModel,
    view: ConsentTextView,
) : Step<ConsentTextModel, Boolean>(id, name, model, view, { true }) {
    @Composable
    override fun Render(callbackCollection: CallbackCollection) =
        view.Render(model, callbackCollection, null)
}
