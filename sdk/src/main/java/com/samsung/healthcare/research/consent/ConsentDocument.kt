package com.samsung.healthcare.research.consent

class ConsentDocument(
    val title: String,
    private val sections: List<ConsentSection>
) {
    fun getSection(index: Int) = sections[index]

    val size = sections.size
}
