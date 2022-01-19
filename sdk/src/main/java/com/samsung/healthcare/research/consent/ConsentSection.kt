package com.samsung.healthcare.research.consent

open class ConsentSection(
    val type: Type,
    val title: String,
    val summary: String,
    val content: String
) {

    enum class Type(val title: String) {
        OVERVIEW("Overview"),

        DATA_GATHERING("Data Gathering"),

        PRIVACY("Privacy"),

        DATA_USE("Data Use"),

        TIME_COMMITMENT("Time Commitment"),

        STUDY_TASKS("Study Tasks"),

        STUDY_SURVEY("Study Survey"),

        WITHDRAWING("Withdrawing"),
    }
}
