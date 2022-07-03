package io.github.bapadua.kt.rabbit.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoRabbitConsumerApplication

fun main(args: Array<String>) {
	runApplication<DemoRabbitConsumerApplication>(*args)
}
