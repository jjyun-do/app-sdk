package com.samsung.healthcare.kit.repository

import com.samsung.healthcare.kit.model.question.ChoiceQuestionModel
import com.samsung.healthcare.kit.task.SurveyTask
import com.samsung.healthcare.kit.task.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.util.UUID

// TODO this file will be deleted. this is ust fake for temporal use.
class RoomTaskRepository : TaskRepository {
    private val upcomingTasks =
        mutableListOf<Task>(
            sampleSurveyTask(),
            sampleSurveyTask(),
        )

    private val completedTasks =
        mutableListOf<Task>()

    override fun done(task: Task) {
        upcomingTasks.remove(task)
        completedTasks.add(task)
    }

    override fun getUpcomingDailyTasks(date: LocalDate): Flow<List<Task>> {
        return flow {
            emit(upcomingTasks.toList())
        }
    }

    override fun getCompletedDailyTasks(date: LocalDate): Flow<List<Task>> {
        return flow {
            emit(completedTasks.toList())
        }
    }

    fun sampleSurveyTask(): SurveyTask =
        SurveyTask.Builder(
            UUID.randomUUID().toString(),
            "Medical History Survey",
            "Please fill out this survey and help us get to know your health condition",
            { }
        ).apply {
            addQuestion(
                ChoiceQuestionModel(
                    "choice-question-model-1",
                    "Do you have a family history of stroke or heart attacks?",
                    "This includes your maternal and paternal grandparents, mother, father, and close relatives.x",
                    candidates = listOf("Yes", "No")
                ),
            )
            addQuestion(
                ChoiceQuestionModel(
                    "choice-question-model-2",
                    "Do you have any of the following symptom(s)?",
                    "Please check all that apply.",
                    candidates = listOf("Yes", "No"),
                    answer = "Yes"
                ),
            )
            addQuestion(
                ChoiceQuestionModel(
                    "choice-question-model-3",
                    "test page?",
                    candidates = listOf("Yes", "No"),
                    answer = "Yes"
                ),
            )
        }.build()
}
