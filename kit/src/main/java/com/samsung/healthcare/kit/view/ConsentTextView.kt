package com.samsung.healthcare.kit.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.samsung.healthcare.kit.R.string
import com.samsung.healthcare.kit.common.CallbackCollection
import com.samsung.healthcare.kit.common.health.HealthPlatformManager
import com.samsung.healthcare.kit.model.ConsentTextModel
import com.samsung.healthcare.kit.step.sub.SubStepHolder
import com.samsung.healthcare.kit.view.layout.ConsentTextLayout
import com.samsung.healthcare.kit.view.layout.SignatureLayout
import java.util.concurrent.TimeUnit

class ConsentTextView(
    private val buttonText: String = "Done",
) : View<ConsentTextModel>() {
    @Composable
    override fun Render(
        model: ConsentTextModel,
        callbackCollection: CallbackCollection,
        holder: SubStepHolder?,
    ) {
        var mutableSvg = rememberSaveable { mutableStateOf("") }
        var signaturePadVisible by rememberSaveable { mutableStateOf(false) }

        ConsentTextLayout(
            mutableSvg.value,
            model,
            buttonText,
            callbackCollection
        ) {
            signaturePadVisible = true
        }

        if (signaturePadVisible)
            SignatureLayout(
                onClickDone = { svg ->
                    mutableSvg.value = svg
                    signaturePadVisible = false
                },
                onClickCancel = { signaturePadVisible = false }
            )
    }
}

@Preview(showBackground = true)
@Composable
fun ConsentTextViewPreview() =
    ConsentTextView().Render(
        ConsentTextModel(
            "id",
            "Consent",
            "Privacy Header",
            stringResource(string.lorem_ipsum_short),
            listOf("I agree", "I agree to share my data.", "Some Message"),
            HealthPlatformManager(
                LocalContext.current,
                listOf(
                    HealthPlatformManager.HealthDataSyncSpec(
                        "HeartRate",
                        15,
                        TimeUnit.MINUTES
                    )
                )
            )
        ),
        CallbackCollection(),
        null
    )
