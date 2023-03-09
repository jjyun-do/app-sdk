package healthstack.kit.task.activity.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import healthstack.kit.R.drawable
import healthstack.kit.annotation.PreviewGenerated
import healthstack.kit.task.activity.model.TappingSpeedResultModel
import healthstack.kit.task.base.CallbackCollection
import healthstack.kit.task.base.View
import healthstack.kit.task.survey.question.SubStepHolder
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomRoundButton
import healthstack.kit.ui.TopBar

class TappingSpeedResultView : View<TappingSpeedResultModel>() {
    @Composable
    override fun Render(
        model: TappingSpeedResultModel,
        callbackCollection: CallbackCollection,
        holder: SubStepHolder?,
    ) {
        Scaffold(
            topBar = {
                TopBar(model.title) {
                    callbackCollection.prev()
                }
            },
            bottomBar = {
                BottomRoundButton("Continue") {
                    callbackCollection.next()
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(56.dp))

                Image(
                    modifier = Modifier
                        .height(360.dp)
                        .width(360.dp),
                    painter = painterResource(id = drawable.ic_tapping_speed_done),
                    contentDescription = "test"
                )

                Spacer(Modifier.height(54.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 56.dp),
                    text = "Great Job!",
                    style = AppTheme.typography.title3,
                    color = AppTheme.colors.textPrimary,
                    textAlign = TextAlign.Center,
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = "Your task was successfully completed.",
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.textPrimary,
                    textAlign = TextAlign.Left,
                )
            }
        }
    }
}

@PreviewGenerated
@Preview(showBackground = true)
@Composable
fun TappingSpeedResultPreview() {
    val view = TappingSpeedResultView()
    val model = TappingSpeedResultModel("id", "Tapping Speed")
    val callbackCollection = object : CallbackCollection() {}
    return view.Render(
        model,
        callbackCollection,
        null
    )
}
