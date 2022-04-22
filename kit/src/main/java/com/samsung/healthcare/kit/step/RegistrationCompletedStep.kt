package com.samsung.healthcare.kit.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.RegistrationCompletedModel
import com.samsung.healthcare.kit.view.RegistrationCompletedView

class RegistrationCompletedStep(
    id: String,
    name: String,
    model: RegistrationCompletedModel,
    view: RegistrationCompletedView,
) : Step<RegistrationCompletedModel, Boolean>(id, name, model, view, { true }) {
    @Composable
    override fun Render(callbackCollection: CallbackCollection) {
        view.Render(model, callbackCollection, null)
    }
}
