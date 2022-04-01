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
    private val layout: @Composable (String, ImageArticle, String, () -> Unit) -> Unit =
        { title, message, buttonText, onComplete ->
            ImageArticleLayout(title, message, buttonText) { onComplete() }
        }
) : Step<Boolean>(id, onCompleted) {
    override val composable: @Composable () -> Unit = {
        layout(title, message, buttonText) {
            completed()
        }
    }

    override fun getResult(): Boolean = true
}
