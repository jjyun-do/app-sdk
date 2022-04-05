package com.samsung.healthcare.research.task

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samsung.healthcare.research.step.QuestionStep
import com.samsung.healthcare.research.step.Step
import com.samsung.healthcare.research.step.SurveyStep
import com.samsung.healthcare.research.survey.ChoiceQuestion
import com.samsung.healthcare.research.view.layout.SurveyStepLayout
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OrderedTaskTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun orderedTask_should_show_steps_in_a_given_order() {
        val dummyQuestion = ChoiceQuestion("", "", "", listOf(""))
        val step1 =
            SurveyStep("s1", "Step1", {}) { title, questionSteps, onCompleted ->
                SurveyStepLayout(title, questionSteps, true, onCompleted)
            }
        val step2 =
            SurveyStep("s2", "Step2", {}) { title, questionSteps, onCompleted ->
                SurveyStepLayout(title, questionSteps, true, onCompleted)
            }
        val step3 =
            SurveyStep("s3", "Step3", {}) { title, questionSteps, onCompleted ->
                SurveyStepLayout(title, questionSteps, true, onCompleted)
            }
        val step4 =
            SurveyStep("s4", "Step4", {}) { title, questionSteps, onCompleted ->
                SurveyStepLayout(title, questionSteps, true, onCompleted)
            }
        step1.addQuestionStep(QuestionStep(dummyQuestion))
        step2.addQuestionStep(QuestionStep(dummyQuestion))
        step3.addQuestionStep(QuestionStep(dummyQuestion))
        step4.addQuestionStep(QuestionStep(dummyQuestion))

        val steps: List<Step<*>> = listOf(step1, step2, step3, step4)

        composeTestRule.setContent {
            OrderedTask(id = "task1", steps = steps)
        }

        composeTestRule.onNode(hasText("Step1")).assertExists()
        step1.completed()
        composeTestRule.onNode(hasText("Step1")).assertDoesNotExist()
        composeTestRule.onNode(hasText("Step2")).assertExists()
        step2.completed()
        composeTestRule.onNode(hasText("Step2")).assertDoesNotExist()
        composeTestRule.onNode(hasText("Step3")).assertExists()
        step3.completed()
        composeTestRule.onNode(hasText("Step3")).assertDoesNotExist()
        composeTestRule.onNode(hasText("Step4")).assertExists()
        step4.completed()
        composeTestRule.onNode(hasText("Step4")).assertDoesNotExist()
    }
}
