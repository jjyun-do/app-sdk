package com.samsung.healthcare.research.view

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickSignInTab_showSignInScreen() {
        composeTestRule.setContent {
            LoginView(
                onClickBack = {},
                onClickMoreVert = {},
                tabSelectedTextColor = Color.Black,
                tabUnselectedTextColor = Color(0x970347F4),
            )
        }

        composeTestRule.onNodeWithText("Sign In").performClick()

        composeTestRule.onNode(hasText("Forgot password")).assertExists()
    }

    @Test
    fun clickSignUpTab_showSignUpScreen() {
        composeTestRule.setContent {
            LoginView(
                onClickBack = {},
                onClickMoreVert = {},
                tabSelectedTextColor = Color.Black,
                tabUnselectedTextColor = Color(0x970347F4),
            )
        }

        composeTestRule.onNodeWithText("Sign In").performClick()

        composeTestRule.onNode(hasText("Forgot password")).assertExists()

        composeTestRule.onNodeWithText("Sign Up").performClick()

        composeTestRule.onNode(hasText("Finish Enrollment")).assertExists()
        composeTestRule.onNode(hasText("Confirm Password")).assertExists()
    }
}
