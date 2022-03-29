package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.intro.Intro
import com.samsung.healthcare.research.view.IntroView

class IntroStep(
    id: String,
    private val intro: Intro,
    onComplete: (Boolean) -> Unit = {},
    private val introView: @Composable (Intro, () -> Unit) -> Unit =
        { i, callback -> IntroView(i, callback) }
) : Step<Boolean>(id, onComplete) {

    override val composable: @Composable () -> Unit = {
        introView(intro) {
            completed()
        }
    }

    override fun getResult(): Boolean = true
}
