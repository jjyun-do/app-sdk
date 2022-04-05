package com.samsung.healthcare.research.view.layout

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samsung.healthcare.research.step.ConsentTextStep
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.theme.darkColors
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

fun testConsentTextStep(checkBoxTexts: List<String>, onCompleted: (Boolean) -> Unit = {}): ConsentTextStep =
    ConsentTextStep("id", "Consent", "SubTitle", "Description", checkBoxTexts, onCompleted) { title, subTitle, description, checkBoxTexts, callback ->
        ConsentTextScreen(title, subTitle, description, checkBoxTexts) { callback() }
    }

@RunWith(AndroidJUnit4::class)
internal class ConsentTextLayoutTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun layout_should_disable_bottombar_button_when_without_all_checked_checkboxes_and_signature() {
        val checkBoxTexts = listOf("1", "2", "3")
        val consentTextStep = testConsentTextStep(checkBoxTexts)

        composeTestRule.setContent {
            consentTextStep.composable()
        }

        composeTestRule.onNodeWithText("Done")
            .assertIsNotEnabled()
    }

    @Test
    fun layout_should_enable_bottombar_button_when_with_all_checked_checkboxes_and_signature() {
        val checkBoxTexts = listOf("1", "2", "3")
        val consentTextStep = testConsentTextStep(checkBoxTexts)

        composeTestRule.setContent {
            AppTheme(darkColors()) {
                consentTextStep.composable()
            }
        }

        val checkBoxes = composeTestRule.onAllNodesWithTag("checkBox")
        for (i: Int in checkBoxTexts.indices) {
            checkBoxes[i].performClick()
        }
        composeTestRule.onNodeWithText("Tap to sign").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithTag("signatureDoneButton").performClick()
        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Done")
            .assertIsEnabled()
    }
}
