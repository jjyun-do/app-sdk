package com.samsung.healthcare.kit.step.sub

import androidx.compose.runtime.Composable
import com.samsung.healthcare.kit.common.CallbackCollection

class SubStepHolder(
    val id: String,
    val name: String,
    val subSteps: List<QuestionSubStep<*, *>>,
) {
    @Composable
    fun Render(callbackCollection: CallbackCollection) {
        subSteps.forEach {
            it.Render(callbackCollection)
        }
    }

    val size: Int = subSteps.size

    fun isSufficient(): Boolean = subSteps.all {
        it.model.isCorrect()
    }
}
