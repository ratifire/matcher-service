package org.ratifire.matcherservice.sender

import io.awspring.cloud.sqs.operations.SqsTemplate
import org.ratifire.matcherservice.dto.PairedParticipantDto
import org.springframework.stereotype.Component

@Component
class ParticipantSender(
    private val rabbitmqTemplate: SqsTemplate
) {

    fun sendMatchedInterviewParticipants(pairedParticipantDto: PairedParticipantDto){
        rabbitmqTemplate.send("matched-participant", pairedParticipantDto)
    }

}