package com.samsung.healthcare.research.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.view.common.BottomBar
import com.samsung.healthcare.research.view.common.TopBar

@Composable
fun ConsentTextLayout(
    signature: String = "",
    title: String,
    subTitle: String,
    description: String,
    checkBoxTexts: List<String>,
    buttonText: String = "Done",
    onClickBack: () -> Unit = {},
    onComplete: () -> Unit = {},
    onClickPad: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopBar(title = title) {
                onClickBack()
            }
        },
        bottomBar = {
            BottomBar(
                text = buttonText,
                buttonEnabled = signature != "" ?: false,
            ) {
                onComplete()
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(innerPadding),
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
                    color = AppTheme.colors.textPrimary
                )
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = description,
                    style = MaterialTheme.typography.body1,
                    lineHeight = 23.sp,
                    color = AppTheme.colors.textPrimary
                )

                checkBoxTexts.forEach {
                    val isChecked = remember { mutableStateOf(false) }
                    LabeledCheckbox(
                        isChecked = isChecked.value,
                        onCheckedChange = { isChecked.value = it },
                        labelText = it,
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 10.dp)
                    .background(color = AppTheme.colors.lightBackground)
                    .clickable {
                        onClickPad()
                    },
            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    text = "Tap to sign",
                )
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
            }
        )
        Text(
            text = labelText,
            style = MaterialTheme.typography.body1,
            color = AppTheme.colors.textPrimary,
        )
    }
}
