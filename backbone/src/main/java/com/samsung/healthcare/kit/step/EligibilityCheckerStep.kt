package com.samsung.healthcare.kit.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.EligibilityCheckerModel
import com.samsung.healthcare.kit.step.sub.SubStepHolder
import com.samsung.healthcare.kit.view.EligibilityCheckerView

class EligibilityCheckerStep(
    id: String,
    name: String,
    model: EligibilityCheckerModel,
    view: EligibilityCheckerView,
    val subStepHolder: SubStepHolder,
) : Step<EligibilityCheckerModel, Boolean>(id, name, model, view, { true }) {
    @Composable
    override fun Render(callbackCollection: CallbackCollection): Unit =
        view.Render(model, callbackCollection, subStepHolder)
}
