package com.samsung.healthcare.research.view.survey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.survey.ChoiceQuestion

@Composable
fun <T> ChoiceQuestionForm(
    choiceQuestion: ChoiceQuestion<T>,
    choiceView: @Composable (ChoiceQuestion<T>, Modifier) -> Unit
) {
    Column {
        QuestionQueryForm(choiceQuestion)

        Spacer(modifier = Modifier.height(20.dp))
        choiceView(
            choiceQuestion,
            Modifier.fillMaxWidth(1f)
        )
    }
}
