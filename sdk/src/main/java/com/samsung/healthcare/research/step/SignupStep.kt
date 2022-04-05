package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.view.SignupView

class SignupStep(
    id: String,
    private val title: String = "SleepCare",
    private val logoDrawableId: Int? = null,
    onComplete: (Boolean) -> Unit = {},
    private val signupView: @Composable (String, Int?, () -> Unit, () -> Unit) -> Unit =
        { title, logoDrawableId, onClickBack, callback ->
            SignupView(
                title,
                logoDrawableId,
                onClickBack,
                callback
            )
        }
) : Step<Boolean>(id, onComplete) {

    override val stepView: @Composable (StepChangedListener) -> Unit = { stepChangedListner ->
        signupView(title, logoDrawableId, stepChangedListner::onStepBack) {
            stepChangedListner.onStepForward()
        }
    }

    override fun getResult(): Boolean = true
}
