package healthstack.kit.task.onboarding.view.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import healthstack.healthdata.link.HealthDataLinkHolder
import healthstack.kit.task.base.CallbackCollection
import healthstack.kit.task.onboarding.model.ConsentTextModel
import healthstack.kit.theme.AppTheme
import healthstack.kit.ui.BottomBarWithGradientBackground
import healthstack.kit.ui.ButtonShape
import healthstack.kit.ui.LabeledCheckbox
import healthstack.kit.ui.TopBar
import kotlinx.coroutines.launch
import java.nio.ByteBuffer

@Composable
fun ConsentTextLayout(
    signature: String = "",
    model: ConsentTextModel,
    buttonText: String,
    callbackCollection: CallbackCollection,
    onClickPad: () -> Unit = {},
) {
    val healthDataLink = HealthDataLinkHolder.getInstance()

    var allChecked by rememberSaveable { mutableStateOf(model.isAllChecked()) }
    val scrollState = rememberScrollState()
    var isEveryPermissionActive by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    SideEffect {
        coroutineScope.launch {
            isEveryPermissionActive = healthDataLink.hasAllPermissions()

            if (!isEveryPermissionActive) healthDataLink.requestPermissions()
        }
    }

    Scaffold(
        topBar = {
            TopBar(title = model.title) {
                callbackCollection.prev()
            }
        },
        bottomBar = {
            BottomBarWithGradientBackground(
                text = buttonText,
                shape = ButtonShape.ROUND,
                buttonEnabled = allChecked && signature.isNotBlank() && isEveryPermissionActive,
            ) {
                callbackCollection.next()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = model.subTitle,
                    color = AppTheme.colors.onSurface,
                    style = AppTheme.typography.body1
                )
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = model.description,
                    style = MaterialTheme.typography.body1,
                    color = AppTheme.colors.onSurface
                )

                model.checkBoxTexts.forEachIndexed { index, consentMessage ->
                    var isChecked by rememberSaveable { mutableStateOf(model.selections[index]) }

                    LabeledCheckbox(
                        isChecked = isChecked,
                        onCheckedChange = { checked ->
                            isChecked = checked
                            model.selections[index] = checked
                            allChecked = model.isAllChecked()
                        },
                        labelText = consentMessage
                    )
                }
            }

            Spacer(Modifier.height(150.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(AppTheme.colors.disabled.copy(0.2F))
                    .clickable {
                        onClickPad()
                    },
            ) {
                if (signature.isBlank())
                    Text(
                        modifier = Modifier.align(alignment = Alignment.Center),
                        text = "Tap to sign.",
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.onSurface
                    )
                else
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = AppTheme.colors.disabled.copy(0.2F)
                    ) {
                        val imageLoader = ImageLoader
                            .Builder(LocalContext.current)
                            .components { add(SvgDecoder.Factory()) }
                            .build()

                        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = ByteBuffer.wrap(signature.toByteArray())
                                ),
                                contentDescription = null,
                            )
                        }
                    }
            }
        }
    }
}
