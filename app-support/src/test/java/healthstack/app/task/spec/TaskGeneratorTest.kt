package healthstack.app.task.spec

import healthstack.backend.integration.task.ChoiceProperties
import healthstack.backend.integration.task.Contents
import healthstack.backend.integration.task.Item
import healthstack.backend.integration.task.Option
import healthstack.backend.integration.task.ScaleProperties
import healthstack.backend.integration.task.TaskSpec
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class TaskGeneratorTest {

    private val startTime = LocalDateTime.now()
    private val endTime = startTime.plusDays(6)
    private val everydayTaskSpec = TaskSpec(
        revisionId = 1,
        taskId = "task-id",
        title = "spec-test",
        description = "good test",
        schedule = "0 0 12 1/1 * ? *",
        startTime = startTime.toString(),
        endTime = endTime.toString(),
        validTime = 600L,
        items = listOf(
            Item(
                name = "q1",
                type = "QUESTION",
                sequence = 0,
                contents = Contents(
                    type = "SCALE",
                    title = "SCALE Question",
                    itemProperties = ScaleProperties(
                        tag = "slider",
                        low = 0,
                        high = 0,
                        lowLabel = "lowest value",
                        highLabel = "highers value",
                    ),
                    required = true,
                )
            ),
            Item(
                name = "q2",
                type = "QUESTION",
                sequence = 0,
                contents = Contents(
                    type = "CHOICE",
                    title = "choice Question",
                    itemProperties = ChoiceProperties(
                        tag = "radio",
                        options = listOf(
                            Option("Choice 1"),
                            Option("Choice 2")
                        )
                    ),
                    required = true,
                )
            )
        )
    )

    @Test
    fun `generate should return tasks with given spec`() {
        val tasks = TaskGenerator.generate(everydayTaskSpec)

        assertEquals(ChronoUnit.DAYS.between(startTime, endTime), tasks.size.toLong())
        tasks.forEach { task ->
            assertEquals(everydayTaskSpec.taskId, task.taskId)
            assertEquals(everydayTaskSpec.revisionId, task.revisionId)
            assertEquals(everydayTaskSpec.title, task.properties.title)
            assertEquals(everydayTaskSpec.description, task.properties.description)

            assertNotNull(task.scheduledAt)
            assertEquals(task.scheduledAt?.plusMinutes(everydayTaskSpec.validTime), task.validUntil)
            assertEquals(everydayTaskSpec.items.size, task.properties.items.size)
        }
    }
}
