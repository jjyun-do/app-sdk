package com.samsung.healthcare.research.task

import com.samsung.healthcare.research.step.Step

class OrderedTask(
    id: String,
    private val steps: List<Step<*>>
) : Task(id)
