package com.samsung.healthcare.research.view.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    title: String = "",
    color: Color = Color(0x970347F4),
    onClickBack: (() -> Unit)?,
    onClickMoreVert: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            if (onClickBack != null) {
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
            Icon(
                imageVector = Icons.Default.MoreVert,
                modifier = Modifier
                    .padding(12.dp)
                    .clickable { onClickMoreVert?.invoke() },
                contentDescription = "",
                tint = color,
            )
        },
        title = {
            Text(
                text = title,
                color = color
            )
        },
        backgroundColor = MaterialTheme.colors.background.copy(alpha = 0f),
        elevation = 0.dp
    )
}

@Composable
fun TopBar(
    title: String,
    onClickBack: (() -> Unit)? = null,
    onClickMoreVert: (() -> Unit)? = null
) {
    TopAppBar(
        navigationIcon = onClickBack?.let { onClick ->
            {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    modifier = Modifier
                        .padding(12.dp)
                        .clickable { onClick() },
                    contentDescription = "back button icon",
                    tint = MaterialTheme.colors.primary,
                )
            }
        },
        actions = {
            onClickMoreVert?.let { onClick ->
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    modifier = Modifier.clickable { onClick() },
                    contentDescription = "more",
                    tint = MaterialTheme.colors.primary,
                )
            }
        },
        title = {
            Text(text = title)
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    )
}

@Preview(showBackground = true)
@Composable
fun ConsentTopBarPreview() {
    TopBar(onClickBack = {}, onClickMoreVert = {})
}
