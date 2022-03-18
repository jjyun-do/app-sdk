package com.samsung.healthcare.research.view.survey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.survey.ChoiceQuestion
import com.samsung.healthcare.research.theme.AppTheme

@Composable
fun <T> RadioChoiceQuestionForm(choiceQuestion: ChoiceQuestion<T>) {
    ChoiceQuestionForm(choiceQuestion) { question, modifier ->
        RadioGroup(question, modifier)
    }
}

@Composable
fun <T> RadioGroup(choiceQuestion: ChoiceQuestion<T>, modifier: Modifier) {
    val rememberIndex = remember { mutableStateOf(choiceQuestion.selection) }

    Column(modifier = modifier) {
        choiceQuestion.candidates.forEachIndexed { index, candidate ->
            Row {
                RadioButton(
                    selected = rememberIndex.value == index,
                    onClick = {
                        rememberIndex.value = index
                        choiceQuestion.selection = index
                    },
                    enabled = true,
                    modifier = Modifier.testTag(candidate.toString()),
                )
                Text(
                    text = candidate.toString(),
                    style = AppTheme.typography.body1,
                    color = AppTheme.colors.textPrimary,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            if (index != choiceQuestion.candidates.size - 1) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RadioChoiceQuestionFormPreview() {
    val choiceQuestion = ChoiceQuestion(
        id = "1",
        query = "Gender",
        explanation = stringResource(id = R.string.lorem_ipsum),
        candidates = listOf("Male", "Female", "Rather not say")
    )
    AppTheme {
        RadioChoiceQuestionForm(choiceQuestion)
    }
}
