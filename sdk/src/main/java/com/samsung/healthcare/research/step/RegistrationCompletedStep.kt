package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.model.SubTitleMessage
import com.samsung.healthcare.research.view.registrationcompleted.RegistrationCompletedScreen

class RegistrationCompletedStep(
    id: String,
    private val title: String,
    private val message: SubTitleMessage,
    private val buttonText: String,
    onCompleted: (Boolean) -> Unit,
    private val layout: @Composable (String, SubTitleMessage, String, () -> Unit) -> Unit =
        { title, message, buttonText, onComplete ->
            RegistrationCompletedScreen(title, message, buttonText) { onComplete() }
        }
) : Step<Boolean>(id, onCompleted) {
    override val composable: @Composable () -> Unit = {
        layout(title, message, buttonText) {
            completed()
        }
    }

    override fun getResult(): Boolean = true
}
