package org.ratifire.matcherservice.listener

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.awspring.cloud.sqs.annotation.SqsListener
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.enums.SqsMessageType
import org.ratifire.matcherservice.service.MatchingService
import org.ratifire.matcherservice.service.ParticipantService
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import java.util.logging.Logger

@Component
class ParticipantListener(
    val participantService: ParticipantService,
    val matchingService: MatchingService,
) {
    private val logger: Logger = Logger.getLogger(ParticipantListener::class.java.name)

    @SqsListener("participantQueue")
    fun participantRouter(@Header("messageType") type: SqsMessageType, payload: String) {
        logger.info("consumed participant event $type")
        val objectMapper = jacksonObjectMapper()

        when (type) {
            SqsMessageType.UPDATE -> participantService.update(jacksonObjectMapper().readValue(payload))
            SqsMessageType.DELETE -> participantService.delete(objectMapper.readTree(payload)["id"].asInt())
            SqsMessageType.CREATE -> participantService.save(
                jacksonObjectMapper().readValue(payload, ParticipantDto::class.java)
            ).let { matchingService.matchParticipant(it) }
        }
    }
}