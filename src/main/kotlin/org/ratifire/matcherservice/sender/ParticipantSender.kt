package org.ratifire.matcherservice.sender

import org.ratifire.matcherservice.dto.PairedParticipantDto
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class ParticipantSender(
    private val rabbitmqTemplate: RabbitTemplate
) {

    fun sendMatchedInterviewParticipants(pairedParticipantDto: PairedParticipantDto){
        rabbitmqTemplate.convertAndSend("matched-participant", pairedParticipantDto)
    }

}