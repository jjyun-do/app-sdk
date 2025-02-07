package healthstack.kit.task.survey.question.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import healthstack.kit.task.base.CallbackCollection
import healthstack.kit.task.survey.question.model.QuestionModel
import healthstack.kit.task.survey.question.model.QuestionModel.QuestionType
import healthstack.kit.theme.AppTheme

abstract class QuestionComponent<T : QuestionModel<*>> : Component<T>() {
    @Composable
    override fun Render(model: T, callbackCollection: CallbackCollection) {
        Text(
            text = model.question,
            style = AppTheme.typography.subtitle1,
            color = AppTheme.colors.onSurface,
        )

        if (!model.explanation.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = model.explanation,
                modifier = Modifier
                    .fillMaxWidth(),
                style = AppTheme.typography.body2,
                color = AppTheme.colors.onBackground.copy(0.6F)
            )
        }
    }

    companion object {
        fun defaultComponentOf(type: QuestionType): Component<out QuestionModel<*>> =
            when (type) {
                QuestionType.Choice -> ChoiceQuestionComponent()
                QuestionType.Text -> TextInputQuestionComponent()
                QuestionType.MultipleChoice -> MultiChoiceQuestionComponent()
            }
    }
}
