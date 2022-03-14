package com.samsung.healthcare.research.survey

class TextInputQuestion(
    title: String,
    description: String,
) : Question<String>(title, description) {
    var text: String = ""

    override fun getResult(): String = text

    override fun getType(): Type = Type.Text
}
