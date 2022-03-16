package com.samsung.healthcare.research.view.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.step.QuestionStep
import com.samsung.healthcare.research.step.SurveyStep
import com.samsung.healthcare.research.survey.ChoiceQuestion
import com.samsung.healthcare.research.survey.TextInputQuestion
import com.samsung.healthcare.research.view.common.BottomBar
import com.samsung.healthcare.research.view.common.TopBar
import com.samsung.healthcare.research.view.survey.SliderChoiceQuestionForm

@Composable
fun MultiPageSurveyLayout(
    title: String,
    questionSteps: List<QuestionStep<*>>,
    onCompleted: () -> Unit,
    onClickBack: () -> Unit = {}
) {
    var index by remember { mutableStateOf(0) }

    Scaffold(
        // TODO should receive callback function(back, more vert) and apply it.
        topBar = { TopBar(title = title, { onClickBack() }, {}) },
        bottomBar = {
            BottomBar(
                leftButtonText = "Previous",
                rightButtonText = if (index == questionSteps.size - 1) "Complete" else "Next",
                onClickLeftButton = { index -= 1 },
                onClickRightButton = {
                    if (index == questionSteps.size - 1) {
                        onCompleted()
                        return@BottomBar
                    }
                    index += 1
                },
                leftButtonEnabled = index != 0
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(horizontal = 24.dp)
        ) {
            SurveyProgressView(
                "${index + 1}/${questionSteps.size}",
                (index + 1) / questionSteps.size.toFloat()
            )
            Spacer(modifier = Modifier.height(64.dp))
            questionSteps[index].composable()
        }
    }
}

@Composable
fun SurveyProgressView(progressText: String, progress: Float) {
    Column {
        Text(progressText)
        Spacer(modifier = Modifier.height(15.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MultiPageSurveyLayoutPreview() {
    val surveyStep =
        SurveyStep("id", "Survey", { }) { title, questionSteps, onCompleted ->
            SurveyStepLayout(title, questionSteps, true, onCompleted)
        }

    val choiceQuestion = ChoiceQuestion(
        title = "Gender",
        description = stringResource(id = R.string.lorem_ipsum),
        candidates = listOf("Male", "Female", "Rather not say")
    )
    surveyStep.addQuestionStep(QuestionStep(choiceQuestion))

    val sliderChoiceQuestion = ChoiceQuestion(
        title = "Slider Choice",
        description = stringResource(id = R.string.lorem_ipsum),
        candidates = listOf("One", "Two", "Three", "Four", "Five")
    )
    surveyStep.addQuestionStep(
        QuestionStep(sliderChoiceQuestion) {
            SliderChoiceQuestionForm(it)
        }
    )

    val textInputQuestion = TextInputQuestion(
        title = "Text Input Question",
        description = stringResource(id = R.string.lorem_ipsum),
    )
    surveyStep.addQuestionStep(QuestionStep(textInputQuestion))

    surveyStep.composable()
}
