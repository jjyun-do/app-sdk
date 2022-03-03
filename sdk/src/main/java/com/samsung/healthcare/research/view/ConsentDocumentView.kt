package com.samsung.healthcare.research.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.view.common.BottomButton
import com.samsung.healthcare.research.view.common.TopBar

@Composable
fun ConsentDocumentView(
    drawableId: Int = R.drawable.ic_consent_section_data_gathering,
    title: String = "Dummy Title",
    subTitle: String = "Dummy SubTitle",
    additionalInfo: String = "Dummy Additional Info",
    topBarTitle: String = "Dummy Bar Title",
    onClickBack: () -> Unit = {},
    onClickCancel: () -> Unit = {},
    buttonText: String = "Dummy Name",
    onClickButton: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar(
                title = topBarTitle,
                onClickBack = onClickBack,
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
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    painter = painterResource(drawableId),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color(0x970347F4))
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
            }
            BottomButton(text= buttonText) { onClickButton }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsentViewPreview() {
    ConsentDocumentView()
}
