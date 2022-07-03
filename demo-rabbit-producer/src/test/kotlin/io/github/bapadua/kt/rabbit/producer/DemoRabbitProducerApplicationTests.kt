package io.github.bapadua.kt.rabbit.producer

import io.github.bapadua.kt.rabbit.producer.message.producer.MessageProducer
import io.github.bapadua.kt.rabbit.producer.message.producer.RabbitMQConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.RabbitMQContainer
import java.time.Duration
import java.util.concurrent.TimeUnit

@ContextConfiguration(initializers = [DemoRabbitProducerApplicationTests.Companion.Initializer::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoRabbitProducerApplicationTests {

    @Autowired
    private lateinit var messageProducer: MessageProducer

    @Autowired
    private lateinit var rabbitTemplate: RabbitTemplate


    companion object {
        const val VHOST = "/localvhost"
        const val USER = "guest"
        const val PASSWORD = "guest"
        const val PORT = 5672

        private var rabbitMQContainer: RabbitMQContainer = RabbitMQContainer("rabbitmq:3-management")
            .withExposedPorts(PORT)
            .withVhost(VHOST)
            .withUser(USER, PASSWORD)
            .withPermission(VHOST, USER, ".*", ".*", ".*")

        class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(applicationContext: ConfigurableApplicationContext) {
                rabbitMQContainer.start()
                val values = TestPropertyValues.of(
                    "spring.rabbitmq.host=${rabbitMQContainer.host}",
                    "spring.rabbitmq.port=${rabbitMQContainer.getMappedPort(PORT)}",
                    "spring.rabbitmq.virtual-host=${VHOST}",
                    "spring.rabbitmq.username=${USER}",
                    "spring.rabbitmq.password=${PASSWORD}"
                )
                values.applyTo(applicationContext)
            }

        }
    }

    @Test
    fun `container should be running`() {
        Assertions.assertTrue(rabbitMQContainer.isRunning)
        val expectedMessage = "test message 123"
        messageProducer.send(expectedMessage)
        val receivedMessage = rabbitTemplate.receive(RabbitMQConfig.QUEUE, Duration.ofSeconds(5).toMillis()).toString()
        Assertions.assertTrue(receivedMessage.contains(expectedMessage))
    }

}
