package com.samsung.healthcare.research.consent

class ConsentDocument(
    val title: String,
    private val sections: List<ConsentSection>
) {
    fun getSection(index: Int): ConsentSection = sections[index]

    val size: Int = sections.size

    enum class ConsentResult {
        Agree,
        Disagree
    }
}
