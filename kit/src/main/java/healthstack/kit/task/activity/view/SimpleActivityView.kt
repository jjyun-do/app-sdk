package healthstack.kit.task.activity.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import healthstack.kit.annotation.PreviewGenerated
import healthstack.kit.task.activity.model.GaitAndBalanceIntroModel
import healthstack.kit.task.activity.model.GaitAndBalanceResultModel
import healthstack.kit.task.activity.model.SimpleViewActivityModel
import healthstack.kit.task.base.CallbackCollection
import healthstack.kit.task.base.View
import healthstack.kit.task.survey.question.SubStepHolder
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomRoundButton
import healthstack.kit.ui.TopBar

class SimpleActivityView : View<SimpleViewActivityModel>() {
    @Composable
    override fun Render(
        model: SimpleViewActivityModel,
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
                if (!model.buttonText.isNullOrEmpty())
                    BottomRoundButton(model.buttonText) {
                        callbackCollection.next()
                    }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(56.dp))

                model.drawableId?.let {
                    Image(
                        modifier = Modifier
                            .size(250.dp),
                        painter = painterResource(it),
                        contentDescription = "image for view"
                    )
                }

                Spacer(Modifier.height(54.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 56.dp),
                    text = model.header,
                    style = AppTheme.typography.title3,
                    color = AppTheme.colors.textPrimary,
                    textAlign = TextAlign.Center,
                )

                Spacer(Modifier.height(32.dp))

                model.body?.let {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        text = it,
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.textPrimary,
                        textAlign = TextAlign.Left,
                    )
                }
            }
        }
    }
}

@PreviewGenerated
@Preview(showBackground = true, device = Devices.NEXUS_6)
@Composable
fun SimpleViewButtonPreview() {
    val view = SimpleActivityView()

    return view.Render(
        GaitAndBalanceIntroModel(
            "id",
            buttonText = "Begin"
        ),
        CallbackCollection(),
        null
    )
}

@PreviewGenerated
@Preview(showBackground = true, device = Devices.NEXUS_6)
@Composable
fun SimpleViewNoButtonPreview() {
    val view = SimpleActivityView()

    return view.Render(
        GaitAndBalanceIntroModel(
            "id",
            "title",
            header = "custom header",
            body = "Walk unassisted for 20 steps in a straight line.\n" +
                "Turn around and walk back to your starting point.\n" +
                "Stand still for 20 seconds.",
        ),
        CallbackCollection(),
        null
    )
}

@PreviewGenerated
@Preview(showBackground = true, device = Devices.NEXUS_6)
@Composable
fun SimpleViewResultPreview() {
    val view = SimpleActivityView()

    return view.Render(
        GaitAndBalanceResultModel(
            id = "id"
        ),
        CallbackCollection(),
        null
    )
}
