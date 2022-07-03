package io.github.bapadua.kt.rabbit.producer.message.producer

import io.github.bapadua.kt.rabbit.producer.config.RabbitMQConfig
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class MessageProducer(private val rabbitTemplate: RabbitTemplate) {

    fun send(message: String) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,  RabbitMQConfig.ROUTING_KEY, message)
    }
}