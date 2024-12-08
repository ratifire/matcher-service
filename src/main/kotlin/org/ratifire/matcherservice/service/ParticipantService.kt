package org.ratifire.matcherservice.service

import org.bson.types.ObjectId
import org.ratifire.matcherservice.converter.MasteryLeveLMapper
import org.ratifire.matcherservice.converter.ParticipantMapper
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.dto.UpdateRequestDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.enums.UpdateAction
import org.ratifire.matcherservice.repository.ParticipantRepository
import org.springframework.stereotype.Service
import kotlin.NoSuchElementException

@Service
class ParticipantService(
    val participantRepository: ParticipantRepository,
    val participantMapper: ParticipantMapper,
    val masteryLevelMapper: MasteryLeveLMapper
) {

    fun save(participant: ParticipantDto): ParticipantEntity {
        val participantEntity = participantMapper.toEntity(participant)
        return participantRepository.save(participantEntity)
    }

    fun save(participant: ParticipantEntity): ParticipantEntity {
        return participantRepository.save(participant)
    }

    fun delete(id: ObjectId) {
        participantRepository.deleteById(id)
    }

    fun update(id: ObjectId, request: UpdateRequestDto) {
        participantRepository.findById(id)
            .map { participant ->
                when (request.action) {
                    UpdateAction.UPDATE -> participant.copy(
                        dates = participant.dates + request.dates,
                        desiredInterview = request.desiredInterview,
                        active = request.desiredInterview > participant.matchedInterview
                    )
                    UpdateAction.REJECT -> participant.copy(
                        dates = participant.dates + request.dates,
                        active = participant.desiredInterview > participant.matchedInterview - 1,
                        matchedInterview = participant.matchedInterview - 1
                    )
                }
            }
            .ifPresentOrElse(
                { updatedParticipant -> participantRepository.save(updatedParticipant) },
                { throw NoSuchElementException("Participant with id: $id not found") }
            )
    }

    fun isParticipantRequestExist(participant: ParticipantDto) = participantRepository.exist(
        participant.participantId,
        participant.specialization,
        masteryLevelMapper.masteryLevelToInt(participant.masteryLevel),
        participant.type
    )
}