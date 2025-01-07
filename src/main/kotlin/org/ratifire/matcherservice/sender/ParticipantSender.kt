package org.ratifire.matcherservice.sender

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.awspring.cloud.sqs.operations.SqsTemplate
import org.ratifire.matcherservice.dto.PairedParticipantDto
import org.springframework.stereotype.Component

@Component
class ParticipantSender(
    private val rabbitmqTemplate: SqsTemplate
) {

    fun sendMatchedInterviewParticipants(pairedParticipantDto: PairedParticipantDto) {
        val payload: String = jacksonObjectMapper().writeValueAsString(pairedParticipantDto)
        rabbitmqTemplate.send("matched-participant", payload)
    }
}