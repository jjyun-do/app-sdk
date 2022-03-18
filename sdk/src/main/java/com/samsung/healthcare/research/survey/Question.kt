package com.samsung.healthcare.research.survey

abstract class Question<R>(
    val id: String,
    val query: String,
    val explanation: String
) {

    abstract fun getResult(): R

    abstract fun getType(): Type

    enum class Type {
        Choice,
        MultipleChoice,
        Text,
    }
}
