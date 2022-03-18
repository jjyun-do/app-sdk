package com.samsung.healthcare.research.view.eligibility

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.eligibility.EligibilityResultMessage
import com.samsung.healthcare.research.theme.AppTheme
import com.samsung.healthcare.research.view.common.BottomRoundButton
import com.samsung.healthcare.research.view.common.TopBar

@Composable
fun EligibilityResultScreen(
    title: String,
    resultMessage: EligibilityResultMessage,
    isEligibility: Boolean,
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
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            resultMessage.drawableId?.let { drawableId ->
                Image(
                    painter = painterResource(drawableId),
                    contentDescription = "Eligibility Image",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    resultMessage.subTitle,
                    style = AppTheme.typography.title2,
                    color = AppTheme.colors.textPrimaryAccent
                )
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    resultMessage.message,
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.textPrimary
                )
            }

            if (isEligibility) {
                BottomRoundButton(text = "Join study") {
                    onComplete()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EligibilityResultScreenPreview() {
    AppTheme {
        val successMessage = EligibilityResultMessage(
            "Great! You’re in!",
            "Congratulations! You are eligible for the study.",
            drawableId = R.drawable.sample_image2
        )
        EligibilityResultScreen(
            "Eligibility Result",
            successMessage,
            true
        )
    }
}
