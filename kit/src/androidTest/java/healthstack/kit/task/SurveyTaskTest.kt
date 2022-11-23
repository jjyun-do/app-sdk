package healthstack.kit.task

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import healthstack.kit.R
import healthstack.kit.task.survey.SurveyTask
import healthstack.kit.task.survey.question.model.MultiChoiceQuestionModel
import healthstack.kit.task.survey.question.model.TextInputQuestionModel
import healthstack.kit.theme.AppTheme
import healthstack.kit.theme.darkBlueColors
import java.lang.Thread.sleep
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class SurveyTaskTest {
    @get:Rule
    val rule = createComposeRule() as AndroidComposeTestRule<*, *>

    private val surveyTask = SurveyTask.Builder(
        id = "task-1",
        revisionId = 1,
        taskId = "survey-sample",
        name = "sample survey",
        description = "test",
        callback = {},
    ).apply {
        addQuestion(
            MultiChoiceQuestionModel(
                id = "q-1",
                query = "choice multiple answer",
                explanation = "this is a sample for MultiChoiceQuestionModel",
                drawableId = null,
                answer = null,
                candidates = listOf("a", "b", "c")
            )
        )

        addQuestion(
            TextInputQuestionModel(
                id = "q-2",
                query = "input some text",
                explanation = "sample for text input",
                drawableId = null,
                answer = null,
            )
        )
    }.build()

    @Test
    fun testSurveyTaskCardView() {
        surveyTask.isCompleted = false
        rule.setContent {
            AppTheme(darkBlueColors()) {
                surveyTask.CardView {
                }
            }
        }
        rule.onNodeWithText(rule.activity.getString(R.string.start_task))
            .assertExists()
    }

    @Test
    fun testCompletedSurveyTaskCardView() {
        surveyTask.isCompleted = true
        rule.setContent {
            AppTheme(darkBlueColors()) {
                surveyTask.CardView {
                }
            }
        }
        rule.onNodeWithText(rule.activity.getString(R.string.start_task))
            .assertDoesNotExist()
    }

    @Test
    fun testSurveyTask() {
        surveyTask.isCompleted = false
        var completed = false
        surveyTask.callback = { completed = true }
        rule.setContent {
            AppTheme(darkBlueColors()) {
                surveyTask.Render()
            }
        }
        sleep(3000)
        getNextButton(rule.activity.getString(R.string.next))
            .assertExists()
            .performClick()

        rule.onNodeWithTag("TextQuestionInputField")
            .performTextInput("text")

        getNextButton(rule.activity.getString(R.string.complete))
            .assertExists()
            .performClick()

        assertTrue(completed)
    }

    private fun getNextButton(buttonName: String) =
        rule.onNodeWithText(buttonName)
}
