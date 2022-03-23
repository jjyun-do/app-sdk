package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.view.layout.ConsentTextLayout

class ConsentTextStep(
    id: String,
    private val title: String,
    private val subTitle: String,
    private val description: String,
    private val checkBoxTexts: List<String>,
    onCompleted: (Boolean) -> Unit,
    private val layout: @Composable (String, String, String, List<String>, () -> Unit) -> Unit =
        { title, subTitle, description, checkBoxTexts, callback ->
            ConsentTextLayout(title, subTitle, description, checkBoxTexts) { callback() }
        }
) : Step<Boolean>(id, onCompleted) {
    override val composable: @Composable () -> Unit = {
        layout(title, subTitle, description, checkBoxTexts) {
            completed()
        }
    }

    override fun getResult(): Boolean = true
}
