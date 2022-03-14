package com.samsung.healthcare.research.survey

abstract class Question<R>(
    val title: String,
    val description: String
) {

    abstract fun getResult(): R

    abstract fun getType(): Type

    enum class Type {
        Choice,
        MultipleChoice,
        Text,
    }
}
