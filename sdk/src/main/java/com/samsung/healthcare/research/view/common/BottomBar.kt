package com.samsung.healthcare.research.view.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(
    color: Color = Color(0x970347F4),
    onClickDisagree: () -> Unit,
    onClickAgree: () -> Unit
) {
    BottomAppBar(
        backgroundColor = Color(0xFFEFF5FA),
        elevation = 5.dp,
    ) {
        Text(
            text = "Disagree",
            color = color,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clickable { onClickDisagree.invoke() }
        )
        Spacer(modifier = Modifier.weight(1f, true))
        Text(
            text = "Agree",
            color = color,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .clickable { onClickAgree.invoke() }
        )
    }
}

@Composable
fun BottomBar(
    leftButtonText: String,
    rightButtonText: String,
    onClickLeftButton: () -> Unit,
    onClickRightButton: () -> Unit,
    leftButtonEnabled: Boolean = true,
    rightButtonEnabled: Boolean = true,
) {

    BottomAppBar(
        backgroundColor = MaterialTheme.colors.background
    ) {
        BottomBarText(leftButtonText, leftButtonEnabled, onClickLeftButton)
        Spacer(modifier = Modifier.weight(1f, true))
        BottomBarText(rightButtonText, rightButtonEnabled, onClickRightButton)
    }
}

@Composable
private fun BottomBarText(
    text: String,
    enabled: Boolean,
    onClickButton: () -> Unit
) {
    Text(
        text = text,
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .alpha(if (enabled) 1f else ContentAlpha.disabled)
            .clickable(enabled = enabled) {
                onClickButton()
            }
    )
}
