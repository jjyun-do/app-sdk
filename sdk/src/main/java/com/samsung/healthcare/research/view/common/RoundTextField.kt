package com.samsung.healthcare.research.view.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.samsung.healthcare.research.theme.AppTheme

@Composable
fun RoundTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = AppTheme.colors.textPrimary,
        backgroundColor = AppTheme.colors.background,
        disabledTextColor = Color.Transparent,
        cursorColor = AppTheme.colors.textPrimary,
        focusedBorderColor = AppTheme.colors.border,
        unfocusedBorderColor = AppTheme.colors.border,
    ),
    isPassword: Boolean = false,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { newText -> onValueChange(newText) },
        singleLine = true,
        shape = RoundedCornerShape(50),
        placeholder = { Text(text = hint, color = AppTheme.colors.textHint) },
        colors = colors,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
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
