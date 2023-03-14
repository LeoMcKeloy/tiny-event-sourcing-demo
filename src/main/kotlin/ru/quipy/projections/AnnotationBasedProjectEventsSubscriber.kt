package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent

@Service
@AggregateSubscriber(
    aggregateClass = ProjectAggregate::class, subscriberName = "demo-subs-stream"
)
class AnnotationBasedProjectEventsSubscriber {

    val logger: Logger = LoggerFactory.getLogger(AnnotationBasedProjectEventsSubscriber::class.java)

    @SubscribeEvent
    fun projectRenamedSubscriber(event: ProjectRenamedEvent) {
        logger.info("Project renamed: {}", event.title)
    }

    @SubscribeEvent
    fun statusCreatedSubscriber(event: StatusCreatedEvent) {
        logger.info("Status created: {}", event.statusName)
    }

    @SubscribeEvent
    fun statusDeletedSubscriber(event: StatusDeletedEvent) {
        logger.info("Status deleted: {}", event.statusName)
    }

    @SubscribeEvent
    fun memberAddedSubscriber(event: MemberAddedEvent) {
        logger.info("Member added: {}", event.memberId)
    }

    @SubscribeEvent
    fun memberDeletedSubscriber(event: MemberDeletedEvent) {
        logger.info("Member deleted: {}", event.memberId)
    }
}