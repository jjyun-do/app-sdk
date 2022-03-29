package com.samsung.healthcare.research.task

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.samsung.healthcare.research.step.Step

@Composable
fun OrderedTask(
    id: String,
    steps: List<Step<*>>,
) {
    var cursor by rememberSaveable { mutableStateOf(-1) }
    if (cursor == -1) {
        steps.forEach { curStep ->
            val onComplete = curStep.onComplete as ((Any?) -> Unit)

            curStep.onComplete = { r ->
                onComplete(r)
                cursor = cursor + 1
                Log.d("OrderedTask", "current cursor: $cursor")
            }
        }

        cursor = 0
    }

    if (cursor >= steps.size) {
        return
    }

    steps[cursor].composable()
}
