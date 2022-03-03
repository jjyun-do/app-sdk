package com.samsung.healthcare.research.view

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickButton_splashView() {
        composeTestRule.setContent {
            SplashView(
                title = "Test Title",
                versionText = "Test Version",
                subTitle = "Test Subtitle",
            )
        }

        composeTestRule.onNodeWithText("Open source licences").performClick()

        val title = composeTestRule.onNode(hasText("Open Source Licence"))
        title.assertExists()
        val close = composeTestRule.onNode(hasText("Close"))
        close.assertExists()
    }

    @Test
    fun clickClose_openSourceLicences() {
        composeTestRule.setContent {
            SplashView(
                title = "Test Title",
                versionText = "Test Version",
                subTitle = "Test Subtitle",
            )
        }

        composeTestRule.onNodeWithText("Open source licences").performClick()
        composeTestRule.onNodeWithText("Close").performClick()

        val title = composeTestRule.onNode(hasText("Open Source Licence"))
        title.assertDoesNotExist()
    }

    @Test
    fun pressBack_openSourceLicences() {
        composeTestRule.setContent {
            SplashView(
                title = "Test Title",
                versionText = "Test Version",
                subTitle = "Test Subtitle",
            )
        }

        composeTestRule.onNodeWithText("Open source licences").performClick()
        Espresso.pressBack()

        val title = composeTestRule.onNode(hasText("Open Source Licence"))
        title.assertDoesNotExist()
    }
}
