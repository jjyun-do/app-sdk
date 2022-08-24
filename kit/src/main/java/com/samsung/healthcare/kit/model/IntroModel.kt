package com.samsung.healthcare.kit.model

import com.samsung.healthcare.kit.R

class IntroModel(
    id: String,
    title: String,
    drawableId: Int? = R.drawable.sample_image4,
    val logoDrawableId: Int? = null,
    // TODO to class
    val summaries: List<Pair<Int, String>>? = null,
    val sections: List<IntroSection>,
) : Model(id, title, drawableId) {

    data class IntroSection(val subTitle: String, val description: String)
}
