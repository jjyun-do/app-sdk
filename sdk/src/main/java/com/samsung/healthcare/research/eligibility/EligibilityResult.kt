package com.samsung.healthcare.research.eligibility

import com.samsung.healthcare.research.model.ImageArticle

class EligibilityResult(
    val title: String,
    private val successMessage: ImageArticle,
    private val failMessage: ImageArticle,
) {
    fun getResultMessage(isEligibility: Boolean): ImageArticle =
        if (isEligibility) successMessage
        else failMessage
}

class EligibilityResultMessage(
    val subTitle: String,
    val message: String,
    // TODO handle various image type
    val drawableId: Int? = null
)
