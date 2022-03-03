package com.samsung.healthcare.research.view.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(color: Color = Color(0x970347F4), onClickDisagree: () -> Unit, onClickAgree: () -> Unit) {
    BottomAppBar(
        backgroundColor = Color(0xFFEFF5FA),
        elevation = 5.dp,
    ) {
        Text(
            text = "Disagree",
            color = color,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(horizontal = 12.dp).clickable { onClickDisagree.invoke() }
        )
        Spacer(modifier = Modifier.weight(1f, true))
        Text(
            text = "Agree",
            color = color,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(horizontal = 12.dp).clickable { onClickAgree.invoke() }
        )
    }
}
