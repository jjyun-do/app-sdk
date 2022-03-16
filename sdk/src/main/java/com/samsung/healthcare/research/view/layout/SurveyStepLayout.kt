package com.samsung.healthcare.research.view.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.samsung.healthcare.research.step.QuestionStep

@Composable
fun SurveyStepLayout(
    title: String,
    questionSteps: List<QuestionStep<*>>,
    pageable: Boolean = true,
    onCompleted: () -> Unit = {},
    onClickBack: () -> Unit = {}
) {
    if (pageable) {
        MultiPageSurveyLayout(title, questionSteps, onCompleted, onClickBack)
    } else {
        SinglePageSurveyLayout(title, questionSteps, onCompleted, onClickBack)
    }
}

// TODO should apply PreviewParameterProvider
class QuestionStepProvider : PreviewParameterProvider<QuestionStep<*>> {
    override val values: Sequence<QuestionStep<*>> =
        // NOTE can not get string from android resource
        TODO("not implemented")
}
