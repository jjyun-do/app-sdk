package com.samsung.healthcare.research.task

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.samsung.healthcare.research.step.Step
import com.samsung.healthcare.research.step.StepChangedListener

@Composable
fun OrderedTask(
    id: String,
    steps: List<Step<*>>
) {
    var cursor by rememberSaveable { mutableStateOf(0) }

    val stepSwitcher = object : StepChangedListener {
        override fun onStepBack() {
            if (0 < cursor) cursor -= 1
        }

        override fun onStepForward() {
            cursor += 1
        }
    }

    if (cursor >= steps.size) {
        return
    }

    steps[cursor].stepView(stepSwitcher)
}
