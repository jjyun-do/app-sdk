package com.samsung.healthcare.research.view.survey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.survey.TextInputQuestion
import com.samsung.healthcare.research.theme.AppTheme

@Composable
fun TextInputQuestionForm(question: TextInputQuestion) {
    Column {
        QuestionQueryForm(question)

        Spacer(modifier = Modifier.height(29.dp))
        var text by remember { mutableStateOf(question.text) }
        InputTextField(
            text,
            Modifier
                .fillMaxWidth(1f)
                .testTag("QuestionInputField"),
        ) {
            text = it
            question.text = text
        }
    }
}

@Composable
private fun InputTextField(text: String, modifier: Modifier, onValueChange: (String) -> Unit) {
    TextField(
        value = text,
        modifier = modifier,
        placeholder = { Text("Input text") },
        colors = TextFieldDefaults.textFieldColors(
            textColor = AppTheme.colors.textPrimary,
            backgroundColor = AppTheme.colors.background,
            unfocusedIndicatorColor = AppTheme.colors.primary
        ),
        singleLine = true,
        onValueChange = { onValueChange(it) },
    )
}

@Preview(showBackground = true)
@Composable
fun TextInputQuestionFormPreview() {
    val question = TextInputQuestion(
        id = "1",
        query = "Text Input Question",
        explanation = stringResource(id = R.string.lorem_ipsum),
    )
    AppTheme {
        TextInputQuestionForm(question)
    }
}
