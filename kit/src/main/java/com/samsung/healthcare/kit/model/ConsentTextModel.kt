package com.samsung.healthcare.kit.model

import com.samsung.healthcare.kit.common.health.HealthPlatformManager

class ConsentTextModel(
    id: String,
    title: String,
    val subTitle: String,
    val description: String,
    val checkBoxTexts: List<String>,
    val healthPlatformManager: HealthPlatformManager,
    drawableId: Int? = null,
) : Model(id, title, drawableId)
