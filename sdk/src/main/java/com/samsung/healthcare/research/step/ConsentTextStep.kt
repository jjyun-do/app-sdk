package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable

class ConsentTextStep(
    id: String,
    private val title: String,
    private val subTitle: String,
    private val description: String,
    private val checkBoxTexts: List<String>,
    onCompleted: (Boolean) -> Unit,
    private val layout: @Composable (String, String, String, List<String>, () -> Unit) -> Unit
) : Step<Boolean>(id, onCompleted) {
    override val composable: @Composable () -> Unit = {
        layout(title, subTitle, description, checkBoxTexts) {
            completed()
        }
    }

    override fun getResult(): Boolean = true
}
