package com.samsung.healthcare.research.view.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.step.QuestionStep
import com.samsung.healthcare.research.step.SurveyStep
import com.samsung.healthcare.research.survey.ChoiceQuestion
import com.samsung.healthcare.research.survey.TextInputQuestion
import com.samsung.healthcare.research.view.common.BottomRoundButton
import com.samsung.healthcare.research.view.common.TopBar
import com.samsung.healthcare.research.view.survey.SliderChoiceQuestionForm

@Composable
fun SinglePageSurveyLayout(
    title: String,
    questionSteps: List<QuestionStep<*>>,
    onCompleted: () -> Unit = {},
    onClickBack: () -> Unit = {},
) {
    val scrollSate = rememberScrollState()
    Scaffold(
        // TODO should receive callback function(back, more vert) and apply it.
        topBar = {
            TopBar(
                title = title,
                onClickBack = { onClickBack() },
                onClickMoreVert = {}
            )
        },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollSate)
        ) {
            questionSteps.forEachIndexed { index, questionStep ->
                questionStep.composable()
                Spacer(
                    modifier = Modifier.height(
                        if (index == questionSteps.size - 1) 55.dp else 64.dp
                    )
                )
            }
            BottomRoundButton(text = "Submit") {
                onCompleted()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SinglePageSurveyLayoutPreview() {
    val surveyStep = SurveyStep("id", "Survey", {}) { title, questionSteps, onCompleted ->
        SurveyStepLayout(title, questionSteps, false, onCompleted)
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
