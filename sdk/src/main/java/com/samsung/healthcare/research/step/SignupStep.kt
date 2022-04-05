package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.view.SignupView

class SignupStep(
    id: String,
    private val title: String = "SleepCare",
    private val logoDrawableId: Int? = null,
    onComplete: (Boolean) -> Unit = {},
    private val signupView: @Composable (String, Int?, () -> Unit) -> Unit =
        { title, logoDrawableId, callback -> SignupView(title, logoDrawableId, callback) }
) : Step<Boolean>(id, onComplete) {

    override val composable: @Composable () -> Unit = {
        signupView(title, logoDrawableId) {
            completed()
        }
    }

    override fun getResult(): Boolean = true
}
