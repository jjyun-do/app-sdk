package com.samsung.healthcare.research.consent

open class ConsentSection(
    val type: Type,
    val title: String,
    val summary: String,
    val content: String
) {

    enum class Type(val title: String) {
        Overview("Overview"),

        DataGathering("Data Gathering"),

        Privacy("Privacy"),

        DataUse("Data Use"),

        TimeCommitment("Time Commitment"),

        StudyTasks("Study Tasks"),

        StudySurvey("Study Survey"),

        Withdrawing("Withdrawing"),
    }
}
