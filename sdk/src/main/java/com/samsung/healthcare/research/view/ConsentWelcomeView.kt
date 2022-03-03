package com.samsung.healthcare.research.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.view.common.BottomButton
import com.samsung.healthcare.research.view.common.TopBar

@Composable
fun ConsentWelcomeView(
    title: String = "Welcome",
    subTitle: String = "Dummy SubTitle",
    additionalInfo: String = "Dummy Additional Info",
    topBarTitle: String = "",
    onClickCancel: () -> Unit = {},
    buttonText: String = "Get Started",
    onClickButton: () -> Unit = {}
) {
    Scaffold(
    topBar = {
        TopBar(
            title = topBarTitle,
            onClickBack = null,
            onClickCancel = onClickCancel
        )
    }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = subTitle,
                            style = MaterialTheme.typography.subtitle1
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = additionalInfo,
                            style = MaterialTheme.typography.subtitle2,
                            color = Color(0x970347F4)
                        )
                    }
                }
                BottomButton(text= buttonText) { onClickButton }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsentWelcomeViewPreview() {
    ConsentWelcomeView()
}
