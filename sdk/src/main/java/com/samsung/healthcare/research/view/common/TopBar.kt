package com.samsung.healthcare.research.view.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(title: String = "dummy title", color: Color = Color(0x970347F4), onClickBack: (() -> Unit)?, onClickCancel: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            if(onClickBack != null) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable { onClickBack?.invoke() },
                    contentDescription = "back button icon",
                    tint = color,
                )
            }
        },
        actions = {
            Text(
                text = "Cancel",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable { onClickCancel.invoke() },
                color = color,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1
            )
        },
        title = { Text(
            text = title,
            color = color
        ) },
        backgroundColor = Color.White,
        elevation = 0.dp
    )
}

@Preview(showBackground = true)
@Composable
fun ConsentTopBarPreview() {
    TopBar(onClickBack = {}, onClickCancel = {})
}
