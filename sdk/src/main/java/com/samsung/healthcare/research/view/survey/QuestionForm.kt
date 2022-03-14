package com.samsung.healthcare.research.view.survey

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.survey.Question
import com.samsung.healthcare.research.survey.Question.Type.Choice
import com.samsung.healthcare.research.survey.Question.Type.MultipleChoice
import com.samsung.healthcare.research.survey.Question.Type.Text

object QuestionForm {
    @SuppressWarnings("unchecked")
    fun <T : Question<*>> getDefaultForm(question: T): @Composable (T) -> Unit =
        when (question.getType()) {
            Choice -> { _ -> TODO() }
            MultipleChoice -> { _ -> TODO() }
            Text -> { _ -> TODO() }
        }
}
