package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.streams.annotation.AggregateSubscriber
import ru.quipy.streams.annotation.SubscribeEvent

@Service
@AggregateSubscriber(
        aggregateClass = TaskAggregate::class, subscriberName = "demo-subs-stream"
)
class AnnotationBasedTaskEventsSubscriber {

    val logger: Logger = LoggerFactory.getLogger(AnnotationBasedTaskEventsSubscriber::class.java)

    @SubscribeEvent
    fun taskCreatedSubscriber(event: TaskCreatedEvent) {
        logger.info("Task created: {}", event.taskName)
    }

    @SubscribeEvent
    fun taskRenamedSubscriber(event: TaskRenamedEvent) {
        logger.info("Task renamed: {}", event.taskName)
    }

    @SubscribeEvent
    fun executorAddedSubscriber(event: ExecutorAddedEvent) {
        logger.info("Executor added: {}", event.executorName)
    }

    @SubscribeEvent
    fun executorDeletedSubscriber(event: ExecutorDeletedEvent) {
        logger.info("Executor deleted: {}", event.executorName)
    }

    @SubscribeEvent
    fun statusSetSubscriber(event: StatusSetEvent) {
        logger.info("Status set: {}", event.nameStatus)
    }
}