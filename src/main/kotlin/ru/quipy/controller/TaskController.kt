package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.StatusCreatedEvent
import ru.quipy.api.TaskAggregate
import ru.quipy.api.TaskCreatedEvent
import ru.quipy.api.TaskRenamedEvent
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.TaskAggregateState
import ru.quipy.logic.create
import ru.quipy.logic.createStatus
import ru.quipy.logic.renameTask
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(
        val taskEsService: EventSourcingService<UUID, TaskAggregate, TaskAggregateState>
) {

    @PostMapping("/{taskName}")
    fun createTask(@PathVariable name: String) : TaskCreatedEvent {
        return taskEsService.create { it.create(UUID.randomUUID(), name) }
    }

    @PutMapping("/{taskId}")
    fun renameTask(@PathVariable taskId: UUID,
                   @RequestParam name: String) : TaskRenamedEvent {
        return taskEsService.update(taskId) { it.renameTask(name) }
    }
}