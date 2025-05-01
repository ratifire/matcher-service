package org.ratifire.matcherservice.sender

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.ratifire.matcherservice.dto.PairedParticipantDto
import org.springframework.stereotype.Component

@Component
class ParticipantSender(
    private val sqsTemplate: SqsTemplate
) {

    fun sendMatchedInterviewParticipants(pairedParticipantDto: PairedParticipantDto) {
        val objectMapper = jacksonObjectMapper().apply {
            registerModule(JavaTimeModule())
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }
        val payload: String = objectMapper.writeValueAsString(pairedParticipantDto)
        sqsTemplate.send("matched-participant", payload) // todo queque name need to be moved to the config file
    }
}