package healthstack.kit.task.activity.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import healthstack.kit.task.activity.model.RangeOfMotionMeasureModel
import healthstack.kit.task.base.CallbackCollection
import healthstack.kit.task.base.View
import healthstack.kit.task.survey.question.SubStepHolder
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomRoundButton
import healthstack.kit.ui.CircularTimer
import healthstack.kit.ui.ListedText
import healthstack.kit.ui.TopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RangeOfMotionMeasureView : View<RangeOfMotionMeasureModel>() {
    @Composable
    override fun Render(
        model: RangeOfMotionMeasureModel,
        callbackCollection: CallbackCollection,
        holder: SubStepHolder?,
    ) {
        var isCompleted by remember { mutableStateOf(false) }
        val scope = rememberCoroutineScope()

        DisposableEffect(Unit) {
            model.init()

            scope.launch {
                delay(model.timeSeconds * 1000)
                isCompleted = true

                callbackCollection.setActivityResult("${model.handType}_times(ms)", model.timeMillis)
                callbackCollection.setActivityResult("${model.handType}_accelerometer", model.accelerometer)
                callbackCollection.setActivityResult("${model.handType}_gyroscope", model.gyroscope)
            }

            onDispose {
                model.close()
            }
        }

        Scaffold(
            topBar = {
                TopBar(model.title) {
                    callbackCollection.prev()
                }
            },
            bottomBar = {
                BottomRoundButton(
                    model.buttonText,
                    isCompleted,
                ) {
                    callbackCollection.next()
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(26.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularTimer(
                        timeSeconds = model.timeSeconds.toInt(),
                    )
                }

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
