package com.samsung.healthcare.research.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.view.common.RoundButton

@Composable
fun SplashView(
    drawableId: Int = R.drawable.your_logo_light,
    title: String,
    versionText: String,
    subTitle: String,
    buttonText: String = "Open source licences",
    licenceText: String = "",
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        OpenSourceLicencesAlertMessage(
            licenceText = licenceText,
            onDismissRequest = { showDialog = false },
            onClickDismissButton = { showDialog = false }
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 10.dp),
                painter = painterResource(drawableId),
                contentDescription = "",
            )
            Column(
                modifier = Modifier.wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = title,
                    style = MaterialTheme.typography.h4,
                    color = Color(0xB7000000)
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = versionText,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color(0xFF6D6D6D)
                    )
                    Text(
                        modifier = Modifier.padding(vertical = 2.dp),
                        text = subTitle,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color(0xFF6D6D6D)
                    )
                }
            }
        }
        Surface(
            modifier = Modifier
                .wrapContentSize(align = Alignment.BottomCenter)
                .padding(vertical = 30.dp)
        ) {
            RoundButton(text = buttonText) { showDialog = true }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashViewPreview() {
    SplashView(
        licenceText =
        """Copyright (c) 2022 Samsung Inner Source, Sample Project. All Rights Reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.""",
        title = "Study name",
        versionText = "version 0.0.0.0",
        subTitle = "Open source licences"
    )
}
