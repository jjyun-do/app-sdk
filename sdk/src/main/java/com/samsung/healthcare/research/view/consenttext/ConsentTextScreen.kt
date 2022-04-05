package com.samsung.healthcare.research.view.consenttext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.samsung.healthcare.research.R.string
import com.samsung.healthcare.research.view.ConsentTextLayout
import com.samsung.healthcare.research.view.SignatureLayout

@Composable
fun ConsentTextScreen(
    title: String,
    subTitle: String,
    description: String,
    checkBoxTexts: List<String>,
    buttonText: String = "Done",
    onClickBack: () -> Unit = {},
    onComplete: () -> Unit = {}
) {
    var mutableSvg = rememberSaveable { mutableStateOf("") }
    var signaturePadVisible by rememberSaveable { mutableStateOf(false) }

    ConsentTextLayout(
        signature = mutableSvg.value,
        title = title,
        subTitle = subTitle,
        description = description,
        checkBoxTexts = checkBoxTexts,
        buttonText = buttonText,
        onClickBack = onClickBack,
        onComplete = onComplete,
        onClickPad = {
            signaturePadVisible = true
        }
    )

    if (signaturePadVisible) {
        SignatureLayout(
            onClickDone = { svg ->
                mutableSvg.value = svg
                signaturePadVisible = false
            },
            onClickCancel = {
                signaturePadVisible = false
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConsentTextLayoutPreview() {
    ConsentTextScreen(
        title = "Consent",
        subTitle = "Privacy header",
        description = stringResource(string.lorem_ipsum_short),
        checkBoxTexts = listOf("I agree", "I agree to share my data.", "Some Message"),
    )
}
