package com.samsung.healthcare.research.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NextBottomBar(
    onNextPressed: () -> Unit
) {
    Surface(
        elevation = 7.dp,
        modifier = Modifier.border(1.dp, MaterialTheme.colors.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                onClick = onNextPressed,
            ) {
                Text(text = "Next")
            }
        }
    }
}
