package org.ratifire.matcherservice.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {
    @Bean
    fun queue(): Queue {
        return Queue("participantQueue", true)
    }
    @Bean
    fun queue2(): Queue {
        return Queue("matched-participant", true)
    }

    @Bean
    fun messageConverter(): MessageConverter {
        val objectMapper = ObjectMapper().apply {
            findAndRegisterModules()
            registerKotlinModule()
        }
        return Jackson2JsonMessageConverter(objectMapper)
    }
}