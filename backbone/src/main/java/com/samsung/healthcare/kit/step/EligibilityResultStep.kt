package com.samsung.healthcare.kit.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.EligibilityResultModel
import com.samsung.healthcare.kit.view.EligibilityResultView

class EligibilityResultStep(
    id: String,
    name: String,
    model: EligibilityResultModel,
    view: EligibilityResultView,
) : Step<EligibilityResultModel, Boolean>(id, name, model, view, getResult = { true }) {

    val success: Boolean = true

    @Composable
    override fun Render(callbackCollection: CallbackCollection): Unit =
        view.Render(model, callbackCollection, null)
}
