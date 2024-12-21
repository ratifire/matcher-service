package org.ratifire.matcherservice.service

import org.bson.types.ObjectId
import org.ratifire.matcherservice.converter.ParticipantMapper
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.dto.UpdateRequestDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.repository.ParticipantRepository
import org.springframework.stereotype.Service

@Service
class ParticipantService(
    val participantRepository: ParticipantRepository,
    val participantMapper: ParticipantMapper
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

    fun update(id: Long, request: UpdateRequestDto) {
        participantRepository.findByCoreRequestId(id)
            ?.map { participant ->
                participant.copy(
                    dates = request.dates,
                    desiredInterview = request.desiredInterview,
                    matchedInterview = request.matchedInterview,
                    active = request.desiredInterview > request.matchedInterview
                )
            }?.ifPresentOrElse({ updatedParticipant -> participantRepository.save(updatedParticipant) },
                { throw NoSuchElementException("Participant with id: $id not found") })
    }

    fun isParticipantRequestExist(participant: ParticipantDto) = participantRepository.exist(
        participant.participantId,
        participant.specialization,
        participant.masteryLevel,
        participant.type
    )
}