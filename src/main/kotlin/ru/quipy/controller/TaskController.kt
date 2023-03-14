package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(
        val taskEsService: EventSourcingService<UUID, TaskAggregate, TaskAggregateState>
) {

    @PostMapping("/{taskName}/{projectId}")
    fun createTask(@PathVariable taskName: String,
                   @PathVariable projectId: UUID) : TaskCreatedEvent {
        return taskEsService.create { it.create(taskName, projectId) }
    }

    @PutMapping("/{taskId}")
    fun renameTask(@PathVariable taskId: UUID,
                   @RequestParam name: String) : TaskRenamedEvent {
        return taskEsService.update(taskId) { it.renameTask(name) }
    }

    @PutMapping("/{taskId}/{name}")
    fun addExecutor(@PathVariable taskId: UUID,
                    @PathVariable name: String) : ExecutorAddedEvent {
        return taskEsService.update(taskId) { it.addExecutor(name) }
    }

    @PutMapping("/{taskId}/{name}/delete")
    fun deleteExecutor(@PathVariable taskId: UUID,
                       @PathVariable name: String) : ExecutorDeletedEvent {
        return taskEsService.update(taskId) { it.deleteExecutor(name) }
    }
}