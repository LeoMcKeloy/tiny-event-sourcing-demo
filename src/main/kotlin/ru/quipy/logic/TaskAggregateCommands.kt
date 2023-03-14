package ru.quipy.logic

import ru.quipy.api.*
import java.util.*

fun TaskAggregateState.create(name: String, projectId: UUID): TaskCreatedEvent {
    return TaskCreatedEvent(taskId = UUID.randomUUID(), projectId = projectId, taskName = name)
}

fun TaskAggregateState.setStatus(status: StatusEntity): StatusSetEvent {
    return StatusSetEvent(taskId = this.getId(), nameStatus = status.name, colorStatus = status.color)
}

fun TaskAggregateState.addExecutor(name: String): ExecutorAddedEvent {
    return ExecutorAddedEvent(taskId = this.getId(), executorName = name)
}

fun TaskAggregateState.deleteExecutor(name: String): ExecutorDeletedEvent {
    return ExecutorDeletedEvent(taskId = this.getId(), executorName = name)
}

fun TaskAggregateState.renameTask(name: String): TaskRenamedEvent {
    return TaskRenamedEvent(taskId = this.getId(), taskName = name)
}
