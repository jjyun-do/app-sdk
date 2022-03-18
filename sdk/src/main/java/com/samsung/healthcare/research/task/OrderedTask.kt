package com.samsung.healthcare.research.task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.samsung.healthcare.research.step.Step

@Composable
fun OrderedTask(
    id: String,
    steps: List<Step<*>>,
) {
    var cursor by remember { mutableStateOf(0) }

    if (cursor >= steps.size) {
        return
    }

    val curStep = steps[cursor]
    val onComplete = curStep.onComplete as ((Any?) -> Unit)

    curStep.onComplete = { r ->
        onComplete(r)
        cursor = cursor + 1
    }
    curStep.composable()
}
