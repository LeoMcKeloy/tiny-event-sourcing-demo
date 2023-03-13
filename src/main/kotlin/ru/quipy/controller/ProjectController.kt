package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.*
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import java.util.*

@RestController
@RequestMapping("/projects")
class ProjectController(
    val projectEsService: EventSourcingService<UUID, ProjectAggregate, ProjectAggregateState>
) {

    @PostMapping("/{projectTitle}")
    fun createProject(@PathVariable projectTitle: String, @RequestParam creatorId: String) : ProjectCreatedEvent {
        return projectEsService.create { it.create(UUID.randomUUID(), projectTitle, creatorId) }
    }

    @PutMapping("/{projectId}/status")
    fun createStatus(@PathVariable projectId: UUID,
                     @RequestParam name: String,
                     @RequestParam color: String) : StatusCreatedEvent {
        return projectEsService.update(projectId) { it.createStatus(name, color) }
    }

    @PutMapping("/{projectId}/status/delete")
    fun deleteStatus(@PathVariable projectId: UUID,
                     @RequestParam name: String,
                     @RequestParam color: String) : StatusDeletedEvent {
        return projectEsService.update(projectId) { it.deleteStatus(name, color) }
    }

    @GetMapping("/{projectId}")
    fun getAccount(@PathVariable projectId: UUID) : ProjectAggregateState? {
        return projectEsService.getState(projectId)
    }

    @PutMapping("/{projectId}/{id}")
    fun addMember(@PathVariable projectId: UUID,
                    @PathVariable id: UUID) : MemberAddedEvent {
        return projectEsService.update(projectId) { it.addMember(id) }
    }

    @PutMapping("/{projectId}/{id}/delete")
    fun deleteMember(@PathVariable projectId: UUID,
                       @PathVariable id: UUID) : MemberDeletedEvent {
        return projectEsService.update(projectId) { it.deleteMember(id) }
    }

    @PutMapping("/{projectId}/{projectTitle}")
    fun renameProject(@PathVariable projectId: UUID,
                      @PathVariable projectTitle: String) : ProjectRenamedEvent {
        return projectEsService.update(projectId) { it.renameProject(projectTitle) }
    }
}