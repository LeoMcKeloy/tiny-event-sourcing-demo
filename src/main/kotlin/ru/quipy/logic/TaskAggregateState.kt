package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

class TaskAggregateState: AggregateState<UUID, TaskAggregate> {
    private lateinit var taskId: UUID
    private lateinit var projectId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var taskName: String
    lateinit var status: StatusEntity
    var executors = mutableSetOf<String>()

    override fun getId() = taskId

    @StateTransitionFunc
    fun taskCreatedApply(event: TaskCreatedEvent) {
        taskId = event.taskId
        projectId = event.projectId
        taskName = event.taskName
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun statusSetApply(event: StatusSetEvent) {
        status = StatusEntity(event.nameStatus, event.colorStatus)
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun executorAddedApply(event: ExecutorAddedEvent) {
        executors.add(event.executorName)
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun executorDeletedApply(event: ExecutorDeletedEvent) {
        executors.remove(event.executorName)
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun taskRenamedApply(event: TaskRenamedEvent) {
        taskName = event.name
        updatedAt = createdAt
    }
}
