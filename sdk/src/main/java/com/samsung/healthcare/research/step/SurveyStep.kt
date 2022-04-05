package com.samsung.healthcare.research.step

import androidx.compose.runtime.Composable
import com.samsung.healthcare.research.survey.Question

class SurveyStep(
    id: String,
    private val title: String,
    onCompleted: (List<Question<*>>) -> Unit,
    private val surveyLayout: @Composable (String, List<QuestionStep<*>>, () -> Unit) -> Unit
) : Step<List<Question<*>>>(id, onCompleted) {
    private val questionSteps = mutableListOf<QuestionStep<*>>()

    fun addQuestionStep(questionStep: QuestionStep<*>) {
        questionSteps.add(questionStep)
    }

    override val stepView: @Composable (StepChangedListener) -> Unit = {
        surveyLayout(title, questionSteps) {
            completed()
        }
    }

    override fun getResult(): List<Question<*>> =
        questionSteps.map { step -> step.question }
}
