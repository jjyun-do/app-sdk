package com.samsung.healthcare.research.view.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.view.common.BottomRoundButton
import com.samsung.healthcare.research.view.common.TopBar

@Composable
fun ConsentTextLayout(
    title: String,
    subTitle: String,
    description: String,
    checkBoxTexts: List<String>,
    buttonText: String = "Done",
    onClickBack: () -> Unit = {},
    onComplete: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar(title = title) {
                onClickBack()
            }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = subTitle,
                style = MaterialTheme.typography.h6,
            )
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = description,
                style = MaterialTheme.typography.body1,
                lineHeight = 23.sp
            )

            checkBoxTexts.forEach {
                val isChecked = remember { mutableStateOf(false) }
                LabeledCheckbox(
                    isChecked = isChecked.value,
                    onCheckedChange = { isChecked.value = it },
                    labelText = it,
                )
            }

            BottomRoundButton(
                text = buttonText
            ) {
                onComplete()
            }
        }
    }
}

@Composable
fun LabeledCheckbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    labelText: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
    ) {
        Checkbox(
            modifier = Modifier.padding(end = 10.dp),
            checked = isChecked,
            onCheckedChange = {
                onCheckedChange(it)
            },
            colors = CheckboxDefaults.colors(Color(0xFFFFA40E))
        )
        Text(
            text = labelText,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConsentTextLayoutPreview() {
    ConsentTextLayout(
        title = "Consent",
        subTitle = "Privacy header",
        description = stringResource(R.string.lorem_ipsum_short),
        checkBoxTexts = listOf("I agree", "I agree to share my data.", "Some Message"),
    )
}
