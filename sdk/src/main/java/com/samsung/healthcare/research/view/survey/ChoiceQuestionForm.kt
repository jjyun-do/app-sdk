package com.samsung.healthcare.research.view.survey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.survey.ChoiceQuestion

@Composable
fun <T> ChoiceQuestionForm(
    choiceQuestion: ChoiceQuestion<T>,
    choiceView: @Composable (ChoiceQuestion<T>, Modifier) -> Unit
) {
    Column {
        Text(
            text = choiceQuestion.title,
            fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = choiceQuestion.description,
            modifier = Modifier.fillMaxWidth(1f),
            fontStyle = MaterialTheme.typography.body1.fontStyle,
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.body1.fontSize
        )
        Spacer(modifier = Modifier.height(29.dp))

        choiceView(
            choiceQuestion,
            Modifier.fillMaxWidth(1f)
        )
    }
}
