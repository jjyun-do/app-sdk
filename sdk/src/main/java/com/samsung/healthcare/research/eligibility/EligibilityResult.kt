package com.samsung.healthcare.research.eligibility

class EligibilityResult(
    val title: String,
    private val successMessage: EligibilityResultMessage,
    private val failMessage: EligibilityResultMessage
) {
    fun getResultMessage(isEligibility: Boolean): EligibilityResultMessage =
        if (isEligibility) successMessage
        else failMessage
}

class EligibilityResultMessage(
    val subTitle: String,
    val message: String,
    // TODO handle various image type
    val drawableId: Int? = null
)
