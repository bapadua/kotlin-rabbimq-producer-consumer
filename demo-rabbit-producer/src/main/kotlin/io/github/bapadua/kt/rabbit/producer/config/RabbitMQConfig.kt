package io.github.bapadua.kt.rabbit.producer.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.QueueBuilder
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    companion object {
        const val QUEUE_NAME = "test_queue_a"
        const val EXCHANGE = "demo.exchange"
        const val X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange"
        const val ROUTING_KEY = "demo.rk"
        const val DURABLE = true
        const val AUTO_DELETE = false
    }


    @Bean
    fun exchanges(): Declarables {
        return Declarables(
            DirectExchange(EXCHANGE, DURABLE, AUTO_DELETE),
            DirectExchange(X_DEAD_LETTER_EXCHANGE, DURABLE, AUTO_DELETE),
        )
    }

    @Bean
    fun queues(): Declarables {
        return Declarables(
            QueueBuilder.durable(QUEUE_NAME).ttl(30000).deadLetterExchange(X_DEAD_LETTER_EXCHANGE)
                .deadLetterExchange(ROUTING_KEY)
                .build()
        )
    }

    @Bean
    fun bindings(): Declarables {
        return Declarables(
            Binding(QUEUE_NAME, Binding.DestinationType.QUEUE, EXCHANGE, ROUTING_KEY, null),
        )
    }

    @Bean
    fun jackson2MessageConverter(objectMapper: ObjectMapper) = Jackson2JsonMessageConverter(objectMapper)
}