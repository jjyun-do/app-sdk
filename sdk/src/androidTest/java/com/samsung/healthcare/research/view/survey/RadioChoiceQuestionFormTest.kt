package com.samsung.healthcare.research.view.survey

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samsung.healthcare.research.survey.ChoiceQuestion
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class RadioChoiceQuestionFormTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun getResult_should_return_selected_value_when_selection_is_changed() {
        val choiceQuestion = ChoiceQuestion(
            title = "title",
            description = "description",
            candidates = listOf("Male", "Female", "Rather not say")
        )

        composeTestRule.setContent {
            RadioChoiceQuestionForm(choiceQuestion)
        }
        val selections = arrayOf(2, 0)
        selections.forEach { selection ->
            composeTestRule.onNodeWithTag(choiceQuestion.candidates[selection])
                .performClick()

            assertEquals(choiceQuestion.candidates[selection], choiceQuestion.getResult())
        }
    }
}
