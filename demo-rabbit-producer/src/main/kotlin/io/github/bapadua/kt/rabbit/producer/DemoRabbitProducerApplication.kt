package io.github.bapadua.kt.rabbit.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoRabbitProducerApplication

fun main(args: Array<String>) {
	runApplication<DemoRabbitProducerApplication>(*args)
}
