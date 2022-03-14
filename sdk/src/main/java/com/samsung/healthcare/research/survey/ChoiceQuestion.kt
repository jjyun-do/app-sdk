package com.samsung.healthcare.research.survey

class ChoiceQuestion<R>(
    title: String,
    description: String,
    val candidates: List<R>
) : Question<R>(title, description) {
    var selection: Int = 0
        set(value) {
            if (value < 0 || candidates.size <= value)
                throw IndexOutOfBoundsException()
            field = value
        }

    init {
        candidates.ifEmpty { throw IllegalArgumentException("at least one candidate is required") }
    }

    override fun getResult(): R = candidates[selection]

    override fun getType(): Type =
        Type.Choice
}
