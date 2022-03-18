package com.samsung.healthcare.research.survey

class TextInputQuestion(
    id: String,
    query: String,
    explanation: String,
) : Question<String>(id, query, explanation) {
    var text: String = ""

    override fun getResult(): String = text

    override fun getType(): Type = Type.Text
}
