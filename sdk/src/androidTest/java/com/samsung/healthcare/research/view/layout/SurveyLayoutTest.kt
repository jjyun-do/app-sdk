package com.samsung.healthcare.research.view.layout

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samsung.healthcare.research.step.QuestionStep
import com.samsung.healthcare.research.step.StepChangedListener
import com.samsung.healthcare.research.step.SurveyStep
import com.samsung.healthcare.research.survey.ChoiceQuestion
import com.samsung.healthcare.research.survey.Question
import com.samsung.healthcare.research.survey.TextInputQuestion
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

internal val choiceQuestion by lazy {
    ChoiceQuestion(
        id = "choice",
        query = "Gender",
        explanation = "description",
        candidates = listOf("Male", "Female", "Rather not say")
    )
}

internal val textInputQuestion by lazy {
    TextInputQuestion(
        id = "text",
        query = "title",
        explanation = "description"
    )
}

internal val stepSwitcher = object : StepChangedListener {
    override fun onStepBack() {}

    override fun onStepForward() {}
}

fun testSurveyStep(pageable: Boolean, onCompleted: (List<Question<*>>) -> Unit = {}): SurveyStep =
    SurveyStep("id", "Survey", onCompleted) { title, questionSteps, onComplete ->
        SurveyStepLayout(title, questionSteps, pageable, onComplete)
    }

@RunWith(AndroidJUnit4::class)
internal class MultiPageSurveyLayoutTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun layout_should_disable_previous_button_when_the_first_question() {
        val surveyStep = testSurveyStep(pageable = true)
        surveyStep.addQuestionStep(QuestionStep(choiceQuestion))

        composeTestRule.setContent {
            surveyStep.stepView(stepSwitcher)
        }

        composeTestRule.onNodeWithText("Previous")
            .assertIsNotEnabled()
    }

    @Test
    fun layout_should_show_next_button_when_survey_has_next_question() {
        val surveyStep = testSurveyStep(pageable = true)
        surveyStep.addQuestionStep(QuestionStep(choiceQuestion))
        surveyStep.addQuestionStep(QuestionStep(textInputQuestion))

        composeTestRule.setContent {
            surveyStep.stepView(stepSwitcher)
        }

        composeTestRule.onNodeWithText("Next")
            .assertIsEnabled()
    }

    @Test
    fun layout_should_show_complete_button_when_no_next_question() {
        val surveyStep = testSurveyStep(pageable = true)
        surveyStep.addQuestionStep(QuestionStep(choiceQuestion))

        composeTestRule.setContent {
            surveyStep.stepView(stepSwitcher)
        }

        composeTestRule.onNodeWithText("Complete")
            .assertIsEnabled()
    }

    @Test
    fun multi_step_should_invoke_callback_with_selected_values_when_survey_is_completed() {
        val (selection, text) = setupCallbackTest(true)

        composeTestRule.onNodeWithTag(choiceQuestion.candidates[selection])
            .performClick()

        composeTestRule.onNodeWithText("Next")
            .performClick()

        composeTestRule.onNodeWithTag("QuestionInputField")
            .performTextInput(text)

        composeTestRule.onNodeWithText("Complete")
            .performClick()
    }

    @Test
    fun single_step_should_invoke_callback_with_selected_values_when_survey_is_completed() {
        val (selection, text) = setupCallbackTest(false)

        composeTestRule.onNodeWithTag(choiceQuestion.candidates[selection])
            .performClick()

        composeTestRule.onNodeWithTag("QuestionInputField")
            .performTextInput(text)

        composeTestRule.onNodeWithText("Submit")
            .performClick()
    }

    private fun setupCallbackTest(pageable: Boolean): Pair<Int, String> {
        val selection = 1
        val text = "step_should_return_selected_values_when_survey_is_completed"
        val surveyStep = testSurveyStep(pageable = pageable) { questions ->
            println("result: " + questions[0].getResult())
            println("result: " + questions[1].getResult())
            assertEquals(choiceQuestion.candidates[selection], questions[0].getResult())
            assertEquals(text, questions[1].getResult())
        }
        surveyStep.addQuestionStep(QuestionStep(choiceQuestion))
        textInputQuestion.text = ""
        surveyStep.addQuestionStep(QuestionStep(textInputQuestion))

        composeTestRule.setContent {
            surveyStep.stepView(stepSwitcher)
        }

        return Pair(selection, text)
    }
}
