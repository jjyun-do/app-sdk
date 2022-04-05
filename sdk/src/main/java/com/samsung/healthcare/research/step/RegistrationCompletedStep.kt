package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.model.ImageArticle
import com.samsung.healthcare.research.view.layout.ImageArticleLayout

class RegistrationCompletedStep(
    id: String,
    private val title: String,
    private val message: ImageArticle,
    private val buttonText: String,
    onCompleted: (Boolean) -> Unit,
    private val layout: @Composable (String, ImageArticle, String, () -> Unit, () -> Unit) -> Unit =
        { title, message, buttonText, onClickBack, onComplete ->
            ImageArticleLayout(
                title,
                message,
                buttonText,
                onClickBack = onClickBack
            ) { onComplete() }
        }
) : Step<Boolean>(id, onCompleted) {
    override val stepView: @Composable (StepChangedListener) -> Unit = { stepChangedListener ->
        layout(title, message, buttonText, stepChangedListener::onStepBack) {
            stepChangedListener.onStepForward()
        }
    }

    override fun getResult(): Boolean = true
}
