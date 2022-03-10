package com.samsung.healthcare.research.view.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RoundTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Color.Gray,
        disabledTextColor = Color.Transparent,
        backgroundColor = Color.White,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
    ),
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = { newText -> onValueChange(newText) },
        singleLine = true,
        shape = RoundedCornerShape(50),
        placeholder = { Text(text = hint) },
        colors = colors,
    )
}

@Preview(showBackground = true)
@Composable
fun RoundTextFieldPreview() {
    val valueState = remember { mutableStateOf("") }
    RoundTextField(
        value = valueState.value,
        onValueChange = {},
        hint = "Email",
        colors = TextFieldDefaults.textFieldColors(),
    )
}
