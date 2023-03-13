package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val PROJECT_CREATED_EVENT = "PROJECT_CREATED_EVENT"
const val STATUS_CREATED_EVENT = "STATUS_CREATED_EVENT"
const val STATUS_DELETED_EVENT = "STATUS_DELETED_EVENT"
const val MEMBER_ADDED_EVENT = "MEMBER_ADDED_EVENT"
const val MEMBER_DELETED_EVENT = "MEMBER_DELETED_EVENT"
const val PROJECT_RENAMED_EVENT = "PROJECT_RENAMED_EVENT"

// API
@DomainEvent(name = PROJECT_CREATED_EVENT)
class ProjectCreatedEvent(
    val projectId: UUID,
    val title: String,
    val creatorId: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = PROJECT_CREATED_EVENT,
    createdAt = createdAt,
)

@DomainEvent(name = PROJECT_RENAMED_EVENT)
class ProjectRenamedEvent(
        val projectId: UUID,
        val title: String,
        createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
        name = PROJECT_RENAMED_EVENT,
        createdAt = createdAt,
)

@DomainEvent(name = STATUS_CREATED_EVENT)
class StatusCreatedEvent(
        val projectId: UUID,
        val statusName: String,
        val color: String,
        createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
        name = STATUS_CREATED_EVENT,
        createdAt = createdAt,
)

@DomainEvent(name = STATUS_DELETED_EVENT)
class StatusDeletedEvent(
        val projectId: UUID,
        val statusName: String,
        val color: String,
        createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
        name = STATUS_DELETED_EVENT,
        createdAt = createdAt,
)

@DomainEvent(name = MEMBER_ADDED_EVENT)
class MemberAddedEvent(
        val projectId: UUID,
        val memberId: UUID,
        createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
        name = MEMBER_ADDED_EVENT,
        createdAt = createdAt,
)

@DomainEvent(name = MEMBER_DELETED_EVENT)
class MemberDeletedEvent(
        val projectId: UUID,
        val memberId: UUID,
        createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
        name = MEMBER_DELETED_EVENT,
        createdAt = createdAt,
)