package io.github.bapadua.kt.rabbit.producer.controller

import io.github.bapadua.kt.rabbit.producer.message.producer.MessageProducer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(private val messageProducer: MessageProducer) {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun sendMessage(@RequestParam("message") message: String): ResponseEntity<Void> {
        logger.info("message received: $message")
        messageProducer.send(message)
        return ResponseEntity.ok().build()
    }

}