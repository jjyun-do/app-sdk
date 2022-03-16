package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.eligibility.Eligibility
import com.samsung.healthcare.research.view.eligibility.EligibilityScreen

class EligibilityStep(
    id: String,
    private val eligibility: Eligibility,
    onComplete: (Boolean) -> Unit = {},
    private val eligibilityScreen: @Composable (Eligibility, () -> Unit) -> Unit =
        { e, callback -> EligibilityScreen(e, callback) }
) : Step<Boolean>(id, onComplete) {

    override val composable: @Composable () -> Unit = {
        eligibilityScreen(eligibility) {
            completed()
        }
    }

    override fun getResult(): Boolean = true
}
