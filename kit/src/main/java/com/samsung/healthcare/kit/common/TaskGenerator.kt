package com.samsung.healthcare.kit.common

import com.samsung.healthcare.kit.entity.Task
import com.samsung.healthcare.kit.entity.Task.Properties
import com.samsung.healthcare.kit.external.data.TaskSpec
import org.quartz.CronExpression
import java.time.LocalDateTime
import java.util.Date

class TaskGenerator {
    companion object {
        fun generate(spec: TaskSpec): List<Task> {
            val schedules: MutableList<Date> = mutableListOf()

            val startTime: Date = TimeUtil.stringToDate(spec.startTime)
            val endTime: Date = TimeUtil.stringToDate(spec.endTime)

            val cronExpression = CronExpression(spec.schedule)

            var currentTime: Date = startTime
            while (cronExpression.getTimeAfter(currentTime) <= endTime) {
                currentTime = cronExpression.getTimeAfter(currentTime)
                schedules.add(currentTime)
            }

            val entities = schedules.map {
                Task(
                    null,
                    spec.revisionId,
                    spec.taskId,
                    Properties(spec.title, spec.description, spec.items),
                    null,
                    LocalDateTime.now(),
                    TimeUtil.dateToLocalDateTimeSystem(it),
                    TimeUtil.dateToLocalDateTimeSystem(it).plusMinutes(spec.validTime),
                    null
                )
            }
            return entities
        }
    }
}
