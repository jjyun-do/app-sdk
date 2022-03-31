package com.samsung.healthcare.research.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.view.common.BottomBar
import com.samsung.healthcare.research.view.common.TopBar
import java.nio.ByteBuffer

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
    var checkCount by rememberSaveable { mutableStateOf(0) }
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
                buttonEnabled = (checkCount == checkBoxTexts.size && signature != "") ?: false,
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
                    val isChecked = rememberSaveable { mutableStateOf(false) }
                    LabeledCheckbox(
                        isChecked = isChecked.value,
                        onCheckedChange = {
                            isChecked.value = it
                            if (isChecked.value == true) {
                                checkCount++
                            } else {
                                checkCount--
                            }
                        },
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
                if (signature.isBlank()) {
                    Text(
                        modifier = Modifier.align(alignment = Alignment.Center),
                        text = "Tap to sign",
                    )
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = AppTheme.colors.surface,
                    ) {
                        val imageLoader = ImageLoader.Builder(LocalContext.current)
                            .components {
                                add(SvgDecoder.Factory())
                            }
                            .build()
                        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                            Image(
                                painter = rememberImagePainter(
                                    data = ByteBuffer.wrap(signature.toByteArray())
                                ),
                                contentDescription = null,
                            )
                        }
                    }
                }
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
