package com.samsung.healthcare.research.eligibility

import com.samsung.healthcare.research.survey.Question

class Eligibility(
    val viewType: ViewType = ViewType.Paragraph,
    val title: String = "Eligibility",
    val description: String = "",
    val sections: List<EligibilitySection>,
    // TODO should support various image type, svg, png.
    val drawableId: Int? = null,
    private val eligibilityChecker: EligibilityChecker
) {
    val questions: List<Question<*>> = eligibilityChecker.questions

    fun isEligibility(): Boolean = eligibilityChecker.isEligibility()

    fun eligibilityResultMessage(): EligibilityResultMessage =
        eligibilityChecker.eligibilityResultMessage()

    enum class ViewType() {
        Card,
        Paragraph,
    }
}
