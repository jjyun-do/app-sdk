package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.survey.Question
import com.samsung.healthcare.research.view.survey.QuestionForm

class QuestionStep<T : Question<*>>(
    val question: T,
    private val questionForm: @Composable (T) -> Unit
) {

    constructor(question: T) : this(question, QuestionForm.getDefaultForm(question))

    val composable: @Composable () -> Unit = {
        questionForm(question)
    }
}
