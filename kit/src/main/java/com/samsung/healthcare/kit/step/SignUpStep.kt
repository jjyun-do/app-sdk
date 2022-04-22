package com.samsung.healthcare.kit.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.SignUpModel
import com.samsung.healthcare.kit.view.SignUpView

class SignUpStep(
    id: String,
    name: String,
    model: SignUpModel,
    view: SignUpView,
) : Step<SignUpModel, Boolean>(id, name, model, view, { true }) {
    @Composable
    override fun Render(callbackCollection: CallbackCollection) {
        view.Render(model, callbackCollection, null)
    }
}
