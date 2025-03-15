package org.ratifire.matcherservice.service

import org.ratifire.matcherservice.converter.ParticipantMapper
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.exeption.ParticipantException
import org.ratifire.matcherservice.repository.ParticipantRepository
import org.ratifire.matcherservice.utills.validateParticipant
import org.springframework.stereotype.Service

@Service
class ParticipantService(
    val participantRepository: ParticipantRepository,
    val participantMapper: ParticipantMapper
) {

    fun save(participant: ParticipantDto): ParticipantEntity {
        if (!validateParticipant(participant) || isParticipantRequestExist(participant)) {
            throw ParticipantException("Participant object with ID " + participant.id + " is already registered or " +
                    "have size of time slots")
        }

        val participantEntity = participantMapper.toEntity(participant)
        return participantRepository.save(participantEntity)
    }

    fun save(participant: ParticipantEntity): ParticipantEntity {
        return participantRepository.save(participant)
    }

    fun delete(id: Int) {
        participantRepository.deleteById(id)
    }

    fun update(participant: ParticipantDto) {
        participantRepository.findById(participant.id)
            .map {
            participantMapper.toEntity(participant).copy(
                matchedInterview = it.matchedInterview,
                blackList = it.blackList,
                active = participant.desiredInterview > it.matchedInterview
            )
        }
            .ifPresentOrElse({participantRepository.save(it)},
                { throw NoSuchElementException("Participant with id: ${participant.id} not found") })
    }

    fun isParticipantRequestExist(participant: ParticipantDto) = participantRepository.exist(
        participant.participantId,
        participant.specialization,
        participant.masteryLevel,
        participant.type
    )
}