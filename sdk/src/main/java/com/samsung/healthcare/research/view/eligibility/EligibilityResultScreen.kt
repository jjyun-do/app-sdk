package com.samsung.healthcare.research.view.eligibility

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.eligibility.EligibilityResultMessage
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
            TopBar(title = title, { onClickBack() }, {})
        },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            resultMessage.drawableId?.let { drawableId ->
                Image(
                    painter = painterResource(drawableId),
                    contentDescription = "Eligibility Image",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    resultMessage.subTitle,
                    fontStyle = MaterialTheme.typography.subtitle1.fontStyle
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(resultMessage.message)
            }

            if (isEligibility) {
                BottomRoundButton(text = "Join study") {
                    onComplete()
                }
            }
        }
    }
}
