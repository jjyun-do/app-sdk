package com.samsung.healthcare.research.view.survey

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samsung.healthcare.research.survey.TextInputQuestion
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class TextInputQuestionFormTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun getResult_should_return_input_value() {
        val textInputQuestion = TextInputQuestion(
            title = "title",
            description = "description"
        )

        composeTestRule.setContent {
            TextInputQuestionForm(textInputQuestion)
        }
        val text = "TextInputQuestionFormTest"
        composeTestRule.onNodeWithTag("QuestionInputField")
            .performTextInput(text)

        assertEquals(text, textInputQuestion.getResult())
    }
}
