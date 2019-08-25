package ru.step.artemismqcamel

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jms.core.JmsTemplate
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@SpringBootTest
@RunWith(SpringRunner::class)
class ArtemisIntegrationTest {
    @Autowired
    lateinit var jmsTemplate: JmsTemplate

    @Test
    fun `should send message to queue and read it`(){
        val queue = "testQueue"
        val fileId = UUID.randomUUID()
        jmsTemplate.convertAndSend(queue, fileId)
        jmsTemplate.receiveTimeout = 10_000
        assertThat(jmsTemplate.receiveAndConvert(queue)).isEqualTo(fileId)
    }
}
