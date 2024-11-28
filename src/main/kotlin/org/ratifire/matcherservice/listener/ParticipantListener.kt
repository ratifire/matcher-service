package org.ratifire.matcherservice.listener

import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.service.MatchingService
import org.ratifire.matcherservice.service.ParticipantService
import org.ratifire.matcherservice.utills.validateParticipant
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class ParticipantListener(
    val participantService: ParticipantService,
    val matchingService: MatchingService,
) {
    private val logger: Logger = Logger.getLogger(ParticipantListener::class.java.name)

    @RabbitListener(queues = ["participantQueue"])
    fun handleParticipantMessage(participant: ParticipantDto) {
        logger.info("consumed participant message with id ${participant.participantId}")

        if (!validateParticipantMessage(participant)) {
            logger.warning(" participant message with id ${participant.participantId} is not valid")
            return
        } // add some logic to hand invalid massage

        val participantEntity = participantService.save(participant)
        matchingService.matchParticipant(participantEntity)
    }

 private fun validateParticipantMessage(participant: ParticipantDto) =
        validateParticipant(participant) && !participantService.isParticipantRequestExist(participant)
}