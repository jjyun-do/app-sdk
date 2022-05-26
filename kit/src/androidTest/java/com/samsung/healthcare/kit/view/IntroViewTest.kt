package com.samsung.healthcare.kit.view

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.samsung.healthcare.kit.R
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.model.IntroModel
import org.junit.Rule
import org.junit.Test

class IntroViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // For Readability
    private fun createIntroModel(
        id: String = "id",
        title: String = "SleepCare",
        drawableId: Int = R.drawable.sample_image4,
        logoDrawableId: Int = R.drawable.ic_sample_icon,
        summaries: List<Pair<Int, String>> = listOf(
            R.drawable.ic_watch to "Wear your watch",
        ),
        descriptions: List<Pair<String, String>> = listOf(
            "Description" to "Description1",
        ),
    ): IntroModel =
        IntroModel(
            id, title, drawableId, logoDrawableId, summaries, descriptions
        )

    private fun createIntroView(
        bottomBarText: String = "TestBottomBar",
    ): IntroView =
        IntroView(bottomBarText)

    private fun createCallbackCollection(): CallbackCollection = CallbackCollection()

    @Test
    fun bottomBarRenderSuccess() {
        val introModel = createIntroModel()
        val introView = createIntroView()
        val callbackCollection = createCallbackCollection()

        composeTestRule.setContent {
            introView.Render(introModel, callbackCollection, null)
        }

        composeTestRule.onNodeWithText("TestBottomBar").assertExists()
    }
}
