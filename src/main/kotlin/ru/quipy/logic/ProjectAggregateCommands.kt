package ru.quipy.logic

import ru.quipy.api.*
import java.util.*


// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member functions

fun ProjectAggregateState.create(id: UUID, title: String, creatorId: String): ProjectCreatedEvent {
    return ProjectCreatedEvent(
        projectId = id,
        title = title,
        creatorId = creatorId,
    )
}

fun ProjectAggregateState.createStatus(name: String, color: String): StatusCreatedEvent {
    return StatusCreatedEvent(projectId = this.getId(), statusName = name, color = color)
}

fun ProjectAggregateState.deleteStatus(name: String, color: String): StatusDeletedEvent {
    if (!statuses.contains(StatusEntity(name, color))) {
        throw IllegalArgumentException("Status doesn't exists: $name")
    }
    return StatusDeletedEvent(projectId = this.getId(), statusName = name, color = color)
}

fun ProjectAggregateState.addMember(id: UUID): MemberAddedEvent {
    return MemberAddedEvent(projectId = this.getId(), memberId = id)
}

fun ProjectAggregateState.deleteMember(id: UUID): MemberDeletedEvent {
    if (!members.contains(id)) {
        throw IllegalArgumentException("Member doesn't exists: $id")
    }
    return MemberDeletedEvent(projectId = this.getId(), memberId = id)
}

fun ProjectAggregateState.renameProject(newTitle: String): ProjectRenamedEvent {
    return ProjectRenamedEvent(projectId = this.getId(), title = newTitle)
}