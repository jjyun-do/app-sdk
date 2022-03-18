package com.samsung.healthcare.research.view.survey

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.survey.ChoiceQuestion
import com.samsung.healthcare.research.survey.Question
import com.samsung.healthcare.research.survey.Question.Type.Choice
import com.samsung.healthcare.research.survey.Question.Type.MultipleChoice
import com.samsung.healthcare.research.survey.Question.Type.Text
import com.samsung.healthcare.research.survey.TextInputQuestion
import com.samsung.healthcare.research.theme.AppTheme

object QuestionForm {
    @SuppressWarnings("unchecked")
    fun <T : Question<*>> getDefaultForm(question: T): @Composable (T) -> Unit =
        when (question.getType()) {
            Choice -> { q -> RadioChoiceQuestionForm(q as ChoiceQuestion<*>) }
            MultipleChoice -> { _ -> TODO() }
            Text -> { q -> TextInputQuestionForm(q as TextInputQuestion) }
        }
}

@Composable
fun <T> QuestionQueryForm(question: Question<T>) {
    Text(
        text = question.query,
        style = AppTheme.typography.title3,
        color = AppTheme.colors.textSecondary
    )
    if (question.explanation.isNotBlank()) {
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = question.explanation,
            modifier = Modifier.fillMaxWidth(1f),
            style = AppTheme.typography.body2,
            color = AppTheme.colors.textHint
        )
    }
}
