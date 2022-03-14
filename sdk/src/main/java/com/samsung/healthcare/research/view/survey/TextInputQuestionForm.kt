package com.samsung.healthcare.research.view.survey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.survey.TextInputQuestion

@Composable
fun TextInputQuestionForm(question: TextInputQuestion) {
    Column {
        Text(
            text = question.title,
            fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = question.description,
            fontStyle = MaterialTheme.typography.body1.fontStyle,
            fontWeight = FontWeight.Normal,
            fontSize = MaterialTheme.typography.body1.fontSize
        )
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
            backgroundColor = Color.White,
            unfocusedIndicatorColor = Color.Blue
        ),
        singleLine = true,
        onValueChange = { onValueChange(it) },
    )
}

@Preview(showBackground = true)
@Composable
fun TextInputQuestionFormPreview() {
    val question = TextInputQuestion(
        title = "Text Input Question",
        description = stringResource(id = R.string.lorem_ipsum),
    )
    MaterialTheme {
        TextInputQuestionForm(question)
    }
}
