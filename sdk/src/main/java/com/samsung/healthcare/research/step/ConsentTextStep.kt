package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.view.consenttext.ConsentTextScreen

class ConsentTextStep(
    id: String,
    private val title: String,
    private val subTitle: String,
    private val description: String,
    private val checkBoxTexts: List<String>,
    onCompleted: (Boolean) -> Unit,
    private val layout: @Composable (String, String, String, List<String>, () -> Unit, () -> Unit) -> Unit =
        { title, subTitle, description, checkBoxTexts, onClickBack, callback ->
            ConsentTextScreen(
                title,
                subTitle,
                description,
                checkBoxTexts,
                onClickBack = onClickBack
            ) { callback() }
        }
) : Step<Boolean>(id, onCompleted) {
    override val stepView: @Composable (StepChangedListener) -> Unit = { stepChangedListener ->
        layout(title, subTitle, description, checkBoxTexts, stepChangedListener::onStepBack) {
            stepChangedListener.onStepForward()
        }
    }

    override fun getResult(): Boolean = true
}
