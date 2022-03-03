package com.samsung.healthcare.research.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samsung.healthcare.research.view.common.BottomBar
import com.samsung.healthcare.research.view.common.TopBar

@Composable
fun ConsentReviewView(
    title: String = "Review",
    subTitle: String = "Dummy SubTitle",
    topBarTitle: String = "Dummy Bar Title",
    onClickBack: () -> Unit = {},
    onClickCancel: () -> Unit = {},
    onClickDisagree: () -> Unit = {},
    onClickAgree: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBar(
                title = topBarTitle,
                onClickBack = onClickBack,
                onClickCancel = onClickCancel
            )
        },
        bottomBar = {
            BottomBar(
                onClickDisagree = onClickDisagree,
                onClickAgree = onClickAgree,
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        modifier = Modifier.padding(top = 40.dp),
                        text = title,
                        style = MaterialTheme.typography.h4,
                        color = Color(0xB7000000)
                    )
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(
                            modifier = Modifier.padding(top = 20.dp),
                            text = subTitle,
                            style = MaterialTheme.typography.subtitle1
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 50.dp, horizontal = 10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        text = "Test Consent\n\n" +
                                "Welcome\n\n " +
                                "This survey will ask you three questions and you will also measure your tapping speed by performing a mall activity.\n\n" +
                                "Data Gathering\n\n" +
                                "This survey will ask you three questions and you will also measure your tapping speed by performing a small activity.\n\n" +
                                "Privacy\n\n" +
                                "ABCDEF GHI J KLMNOP. QRST YV WXYZ ABCDEFGHIJ KLM OP QRST. ABCDEF GHI J KLMNOP. QRST YV WXYZ ABCDEFGHIJ KLM OP QRST. ABCDEF GHI J KLMNOP. QRST YV WXYZ ABCDEFGHIJ KLM OP QRST. ABCDEF GHI J KLMNOP. QRST YV WXYZ ABCDEFGHIJ KLM OP QRST. \n\n" +
                                "ABCDEF\n\n" +
                                "ABCDEF GHI J KLMNOP. QRST YV WXYZ ABCDEFGHIJ KLM OP QRST. ABCDEF GHI J KLMNOP. QRST YV WXYZ ABCDEFGHIJ KLM OP QRST. ABCDEF GHI J KLMNOP. QRST YV WXYZ ABCDEFGHIJ KLM OP QRST. ABCDEF GHI J KLMNOP. QRST YV WXYZ ABCDEFGHIJ KLM OP QRST. \n\n",
                        style = MaterialTheme.typography.h6,
                        lineHeight = 25.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConsentReviewViewPreview() {
    ConsentReviewView()
}
