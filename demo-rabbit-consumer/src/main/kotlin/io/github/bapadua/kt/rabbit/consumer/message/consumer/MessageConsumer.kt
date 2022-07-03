package io.github.bapadua.kt.rabbit.consumer.message.consumer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class MessageConsumer {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @RabbitListener(queues = ["test_queue_a"])
    fun receive(message: String) {
        logger.info("message received: $message")
    }

}