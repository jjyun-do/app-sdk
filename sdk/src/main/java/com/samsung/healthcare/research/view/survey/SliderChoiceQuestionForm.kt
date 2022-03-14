package com.samsung.healthcare.research.view.survey

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.samsung.healthcare.research.R
import com.samsung.healthcare.research.survey.ChoiceQuestion
import kotlin.math.roundToInt

@Composable
fun <T> SliderChoiceQuestionForm(choiceQuestion: ChoiceQuestion<T>) {
    ChoiceQuestionForm(choiceQuestion) { question, modifier ->
        SliderGroup(question, modifier)
    }
}

@Composable
fun <T> SliderGroup(question: ChoiceQuestion<T>, modifier: Modifier) {
    var sliderState by remember { mutableStateOf(question.selection) }

    Column(modifier = modifier) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            question.candidates.forEach {
                Text(text = it.toString())
            }
        }
        Slider(
            value = sliderState.toFloat(),
            valueRange = 0f..(question.candidates.size - 1).toFloat(),
            steps = question.candidates.size - 2,
            onValueChange = { newValue ->
                sliderState = newValue.roundToInt()
                question.selection = newValue.roundToInt()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SliderChoiceQuestionFormPreview() {
    val choiceQuestion = ChoiceQuestion(
        title = "Slider Choice",
        description = stringResource(id = R.string.lorem_ipsum),
        candidates = listOf("One", "Two", "Three", "Four", "Five")
    )
    MaterialTheme {
        SliderChoiceQuestionForm(choiceQuestion)
    }
}
