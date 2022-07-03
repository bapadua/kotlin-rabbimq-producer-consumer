package io.github.bapadua.kt.rabbit.producer.message.producer

class RabbitMQConfig {
    companion object {
        val RABBITMQ_IMAGE = "rabbitmq:3.7.25-management-alpine"
        val RABBITMQ_VHOST = "/localvhost"
        val EXCHANGE = "amqp.topic.exchange"
        val EXCHANGE_TYPE = "direct"
        val QUEUE = "amqp.topic.queue"
    }
}