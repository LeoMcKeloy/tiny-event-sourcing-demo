package ru.quipy.projections

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.quipy.api.*
import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import ru.quipy.logic.StatusEntity
import ru.quipy.streams.AggregateSubscriptionsManager
import java.util.*
import javax.annotation.PostConstruct

@Service
class TaskEventsSubscriber {
    val logger: Logger = LoggerFactory.getLogger(TaskEventsSubscriber::class.java)

    @Autowired
    lateinit var subscriptionsManager: AggregateSubscriptionsManager

    @PostConstruct
    fun init() {
        subscriptionsManager.createSubscriber(TaskAggregate::class, "some-meaningful-name") {

            `when`(TaskCreatedEvent::class) { event ->
                logger.info("Task created: {}", event.taskName)
            }

            `when`(ExecutorAddedEvent::class) { event ->
                logger.info("Executor added: {}", event.executorName)
            }

            `when`(ExecutorDeletedEvent::class) { event ->
                logger.info("Executor created: {}", event.executorName)
            }

            `when`(TaskRenamedEvent::class) { event ->
                logger.info("Task renamed: {}", event.taskName)
            }

            `when`(StatusSetEvent::class) { event ->
                logger.info("Task created: {}", event.status.name)
            }
        }
    }
}