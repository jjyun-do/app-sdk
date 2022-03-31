package com.samsung.healthcare.research.view.registrationcompleted

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.model.SubTitleMessage
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.view.common.BottomBar
import com.samsung.healthcare.research.view.common.TopBar

@Composable
fun RegistrationCompletedScreen(
    title: String,
    message: SubTitleMessage,
    buttonText: String,
    onClickBack: () -> Unit = {},
    onComplete: () -> Unit = {}
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
            ) { onComplete() }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(innerPadding),
        ) {
            message.drawableId?.let { drawableId ->
                Image(
                    painter = painterResource(drawableId),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    message.subTitle,
                    style = AppTheme.typography.title2,
                    color = AppTheme.colors.textPrimaryAccent
                )
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    message.description,
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.textPrimary
                )
            }
        }
    }
}
