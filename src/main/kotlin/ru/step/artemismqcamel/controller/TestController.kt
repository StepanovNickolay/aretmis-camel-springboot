package ru.step.artemismqcamel.controller

import org.apache.camel.ProducerTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.core.JmsTemplate
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@CrossOrigin
@RequestMapping("/v1")
class TestController(
        val producerTemplate: ProducerTemplate,
        val jmsTemplate: JmsTemplate
) {
    @Value("\${app.queue.receive}")
    val receiveQueue: String = ""

    @Value("\${app.queue.produce}")
    val produceQueue: String = ""

    @GetMapping("/send")
    fun send(): String? {
        producerTemplate.sendBody("jms:$receiveQueue", UUID.randomUUID())
        return jmsTemplate.receive(produceQueue)?.getBody(UUID::class.java)?.toString()
    }
}
