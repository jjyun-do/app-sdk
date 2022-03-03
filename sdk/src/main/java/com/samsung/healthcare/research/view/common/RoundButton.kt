package com.samsung.healthcare.research.view.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RoundButton(text: String = "", buttonColor: Color = Color(0xFFEAEFF3), textColor: Color = Color.Black, onClick: () -> Unit) {
    Button(
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        onClick = onClick,
    ) {
        Text(
            text = text,
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoundButtonPreview() {
    RoundButton() {}
}
