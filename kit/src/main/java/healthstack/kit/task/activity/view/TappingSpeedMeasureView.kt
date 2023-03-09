package healthstack.kit.task.activity.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import healthstack.kit.R
import healthstack.kit.annotation.PreviewGenerated
import healthstack.kit.task.activity.model.TappingSpeedMeasureModel
import healthstack.kit.task.base.CallbackCollection
import healthstack.kit.task.base.View
import healthstack.kit.task.survey.question.SubStepHolder
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.TopBar
import kotlinx.coroutines.delay

class TappingSpeedMeasureView() : View<TappingSpeedMeasureModel>() {
    @Composable
    override fun Render(
        model: TappingSpeedMeasureModel,
        callbackCollection: CallbackCollection,
        holder: SubStepHolder?,
    ) {
        val ticks = remember { mutableStateOf(model.measureTimeSecond) }
        val tapCount = remember { mutableStateOf(0) }

        Scaffold(
            topBar = {
                TopBar(model.title) {
                    callbackCollection.prev()
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(56.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = ticks.value.toString(),
                    style = AppTheme.typography.title3,
                    color = AppTheme.colors.textPrimary,
                    textAlign = TextAlign.Center,
                )

                LaunchedEffect(Unit) {
                    while (ticks.value > 0) {
                        delay(1000)
                        ticks.value -= 1
                    }
                    callbackCollection.setActivityResult(
                        model.handType, tapCount.value
                    )
                    callbackCollection.next()
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 56.dp),
                    text = "Tap the buttons with your ${model.handType} hand",
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.textPrimary,
                    textAlign = TextAlign.Center,
                )
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .absolutePadding(
                                top = 80.dp
                            ),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_tapping),
                            contentDescription = "tapping icon",
                            modifier = Modifier
                                .size(90.dp)
                                .clickable(
                                    enabled = true,
                                    onClick = {
                                        tapCount.value += 1
                                    }
                                )
                        )
                        Spacer(modifier = Modifier.width(50.dp))
                        Image(
                            painter = painterResource(R.drawable.ic_tapping),
                            contentDescription = "tapping icon",
                            modifier = Modifier
                                .size(90.dp)
                                .clickable(
                                    enabled = true,
                                    onClick = {
                                        tapCount.value += 1
                                    }
                                )
                        )
                    }
                }
            }
        }
    }
}

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun TappingSpeedMeasurePreview() {
    val view = TappingSpeedMeasureView()
    val model = TappingSpeedMeasureModel("id", "Tapping Speed")
    val callbackCollection = object : CallbackCollection() {}
    return view.Render(
        model,
        callbackCollection,
        null
    )
}
