package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.eligibility.Eligibility
import com.samsung.healthcare.research.view.eligibility.EligibilityScreen

class EligibilityStep(
    id: String,
    private val eligibility: Eligibility,
    onComplete: (Boolean) -> Unit = {},
    private val eligibilityScreen: @Composable (Eligibility, () -> Unit, () -> Unit) -> Unit =
        { e, onStepBack, onStepForward -> EligibilityScreen(e, onStepBack, onStepForward) }
) : Step<Boolean>(id, onComplete) {

    override val stepView: @Composable (StepChangedListener) -> Unit = { stepChangedListener ->
        eligibilityScreen(eligibility, stepChangedListener::onStepBack) {
            stepChangedListener.onStepForward()
        }
    }

    override fun getResult(): Boolean = true
}
