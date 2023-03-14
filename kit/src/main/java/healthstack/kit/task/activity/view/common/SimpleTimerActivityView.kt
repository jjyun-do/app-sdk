package healthstack.kit.task.activity.view.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import healthstack.kit.task.activity.model.common.SimpleTimerActivityModel
import healthstack.kit.task.base.CallbackCollection
import healthstack.kit.task.base.View
import healthstack.kit.task.survey.question.SubStepHolder
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.CircularTimer
import healthstack.kit.ui.ListedText
import healthstack.kit.ui.TopBar

open class SimpleTimerActivityView<T : SimpleTimerActivityModel> : View<T>() {
    @Composable
    override fun Render(
        model: T,
        callbackCollection: CallbackCollection,
        holder: SubStepHolder?,
    ) {
        val scrollState = rememberScrollState()

        Scaffold(
            topBar = {
                TopBar(model.title) {
                    callbackCollection.prev()
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(56.dp))

                CircularTimer(timeSeconds = model.timeSeconds)

                Spacer(Modifier.height(54.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = model.header,
                    style = AppTheme.typography.title3,
                    color = AppTheme.colors.onSurface,
                    textAlign = TextAlign.Center,
                )

                Spacer(Modifier.height(32.dp))

                model.body?.let {
                    ListedText(it, model.textType)
                }
            }
        }
    }
}
