package ru.step.artemismqcamel.config

import org.apache.camel.component.jms.JmsComponent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.jms.ConnectionFactory

@Configuration
class CamelConfig {
    @Bean
    fun jmsComponent(connectionFactory: ConnectionFactory) =
            JmsComponent.jmsComponent(connectionFactory)
}
