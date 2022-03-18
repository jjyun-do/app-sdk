package com.samsung.healthcare.research.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.step.ConsentDocumentStep
import com.samsung.healthcare.research.step.QuestionStep
import com.samsung.healthcare.research.step.Step
import com.samsung.healthcare.research.step.SurveyStep
import com.samsung.healthcare.research.survey.ChoiceQuestion
import com.samsung.healthcare.research.survey.Question
import com.samsung.healthcare.research.survey.TextInputQuestion
import com.samsung.healthcare.research.task.OrderedTask
import com.samsung.healthcare.research.view.layout.SurveyStepLayout
import com.samsung.healthcare.research.view.survey.SliderChoiceQuestionForm
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var consentDocumentStep: ConsentDocumentStep

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface {
                HealthApp()
            }
        }
    }
}

@Composable
fun HealthApp() {
    val surveyStep = makeSurveyStep {}
    val steps: List<Step<*>> = listOf(surveyStep)

    OrderedTask(
        id = "id",
        steps = steps,
    )
}

@Composable
fun makeSurveyStep(onComplete: (List<Question<*>>) -> Unit): SurveyStep {
    val surveyStep =
        SurveyStep("survey", "Survey", onComplete) { title, questionSteps, onCompleted ->
            SurveyStepLayout(title, questionSteps, true, onCompleted)
        }

    val choiceQuestion = ChoiceQuestion(
        id = "1",
        query = "Gender",
        explanation = stringResource(id = R.string.lorem_ipsum),
        candidates = listOf("Male", "Female", "Rather not say")
    )
    surveyStep.addQuestionStep(QuestionStep(choiceQuestion))

    val sliderChoiceQuestion = ChoiceQuestion(
        id = "2",
        query = "Slider Choice",
        explanation = stringResource(id = R.string.lorem_ipsum),
        candidates = listOf("One", "Two", "Three", "Four", "Five")
    )
    surveyStep.addQuestionStep(
        QuestionStep(sliderChoiceQuestion) {
            SliderChoiceQuestionForm(it)
        }
    )

    val textInputQuestion = TextInputQuestion(
        id = "3",
        query = "Text Input Question",
        explanation = stringResource(id = R.string.lorem_ipsum),
    )
    surveyStep.addQuestionStep(QuestionStep(textInputQuestion))

    return surveyStep
}

@Preview
@Composable
fun OnboardingPreview() {
    /*val step = ConsentDocumentStep(
        "step-id",
        ConsentDocumentModule.consentDocument
    )

    step.composable()*/
}
