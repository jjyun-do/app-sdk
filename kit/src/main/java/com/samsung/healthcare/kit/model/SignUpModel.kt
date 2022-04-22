package com.samsung.healthcare.kit.model

class SignUpModel(
    id: String,
    title: String,
    val description: String? = null,
    drawableId: Int? = null,
) : Model(id, title, drawableId)
