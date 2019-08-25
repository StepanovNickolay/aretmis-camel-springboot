package ru.step.artemismqcamel.config

import org.springframework.boot.autoconfigure.jms.artemis.ArtemisConfigurationCustomizer
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties
import org.springframework.context.annotation.Configuration
import org.springframework.jms.annotation.EnableJms

@Configuration
@EnableJms
class ArtemisConfig(
        val artemisProperties: ArtemisProperties
): ArtemisConfigurationCustomizer {
    override fun customize(configuration: org.apache.activemq.artemis.core.config.Configuration) {
        configuration.apply {
            addAcceptorConfiguration("netty", "tcp://${artemisProperties.host}:${artemisProperties.port}")
        }
    }
}
