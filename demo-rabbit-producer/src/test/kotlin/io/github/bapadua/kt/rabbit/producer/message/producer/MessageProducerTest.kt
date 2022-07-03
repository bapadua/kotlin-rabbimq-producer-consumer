package io.github.bapadua.kt.rabbit.producer.message.producer

import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.amqp.rabbit.core.RabbitTemplate

@ExtendWith(MockKExtension::class)
internal class MessageProducerTest {

    private var rabbitTemplate = mockk<RabbitTemplate>(relaxed = true)
    private val producer = MessageProducer(rabbitTemplate)

    @Test
    fun `should send a message to the topic`() {
        every { rabbitTemplate.convertAndSend(any(), any(), any<String>()) } returns Unit
        producer.send("message")
        verify(exactly = 1) { rabbitTemplate.convertAndSend(any(), any(), any<String>()) }
    }
}