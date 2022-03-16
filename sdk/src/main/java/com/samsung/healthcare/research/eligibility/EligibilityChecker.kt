package com.samsung.healthcare.research.eligibility

import com.samsung.healthcare.research.survey.Question

class EligibilityChecker(
    val questions: List<Question<*>>,
    private val predicate: (List<Question<*>>) -> Boolean,
    private val eligibilityResult: EligibilityResult
) {
    fun isEligibility(): Boolean = predicate(questions)

    fun eligibilityResultMessage(): EligibilityResultMessage =
        eligibilityResult.getResultMessage(isEligibility())
}
