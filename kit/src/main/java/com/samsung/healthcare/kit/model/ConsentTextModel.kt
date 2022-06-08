package com.samsung.healthcare.kit.model

import com.samsung.healthcare.kit.external.source.HealthPlatformManager

class ConsentTextModel(
    id: String,
    title: String,
    val subTitle: String,
    val description: String,
    val checkBoxTexts: List<String>,
    val healthPlatformManager: HealthPlatformManager? = null,
    drawableId: Int? = null,
) : Model(id, title, drawableId) {
    var encodedSignature: String = ""
    val selections: BooleanArray = BooleanArray(checkBoxTexts.size)

    fun isAllChecked() = selections.all { it }
}
