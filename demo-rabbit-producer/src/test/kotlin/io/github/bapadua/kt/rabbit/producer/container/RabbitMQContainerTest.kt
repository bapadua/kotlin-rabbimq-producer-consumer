package io.github.bapadua.kt.rabbit.producer.container

import io.github.bapadua.kt.rabbit.producer.message.producer.RabbitMQConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.testcontainers.containers.RabbitMQContainer

class RabbitMQContainerTest {

    companion object {
        val container = RabbitMQContainer(RabbitMQConfig.RABBITMQ_IMAGE)
            .apply {
                withExchange(RabbitMQConfig.EXCHANGE, RabbitMQConfig.EXCHANGE_TYPE)
                withQueue(RabbitMQConfig.RABBITMQ_VHOST, RabbitMQConfig.QUEUE)
                withBinding(RabbitMQConfig.RABBITMQ_VHOST, RabbitMQConfig.EXCHANGE, RabbitMQConfig.QUEUE)
                start()
            }
    }

    @Test
    fun `container should be running`() {
        Assertions.assertTrue(container.isRunning)
    }
}