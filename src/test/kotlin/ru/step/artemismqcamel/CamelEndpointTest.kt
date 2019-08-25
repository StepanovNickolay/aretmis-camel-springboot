package ru.step.artemismqcamel

import org.apache.camel.EndpointInject
import org.apache.camel.ProducerTemplate
import org.apache.camel.component.mock.MockEndpoint
import org.apache.camel.test.spring.CamelSpringBootRunner
import org.apache.camel.test.spring.DisableJmx
import org.apache.camel.test.spring.MockEndpoints
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
@RunWith(CamelSpringBootRunner::class)
@DisableJmx
@MockEndpoints
class CamelEndpointTest {
    @Autowired
    lateinit var producerTemplate: ProducerTemplate

    @EndpointInject(uri = "mock:jms:receiveQueue")
    lateinit var mockEndpoint: MockEndpoint

    @Value("\${app.queue.receive}")
    val queue: String = ""

    @Test
    fun `endpoint test`() {
        val id = UUID.randomUUID()
        mockEndpoint.expectedMessageCount(1)
        mockEndpoint.expectedBodiesReceived(id)
        producerTemplate.sendBody("jms:$queue", id)
        mockEndpoint.assertIsSatisfied()
    }
}
