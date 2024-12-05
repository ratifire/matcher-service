package org.ratifire.matcherservice.service

import org.ratifire.matcherservice.converter.MasteryLeveLMapper
import org.ratifire.matcherservice.converter.ParticipantMapper
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.dto.UpdateParticipantDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.exception.InvalidParticipantDataException
import org.ratifire.matcherservice.repository.ParticipantRepository
import org.ratifire.matcherservice.utills.validateParticipant
import org.springframework.stereotype.Service

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

    fun delete(id: String) {
        if (!participantRepository.existsById(id)) {
            throw NoSuchElementException("Participant with id: $id not found")
        }
        participantRepository.deleteById(id)
    }

    fun update(
        id: String,
        desiredInterview: Int,
        updateParticipantDto: UpdateParticipantDto,
        isRejected: Boolean = false
    ) {
        if (!validateParticipant(updateParticipantDto.dates, desiredInterview)) {
            throw InvalidParticipantDataException(" number of dates is less than the desired number of interviews")
        }
        val participantEntity = participantRepository
            .findById(id)
            .orElseThrow { NoSuchElementException("Participant with id: $id not found") }
        val updatedParticipant = participantEntity.copy(
            dates = updateParticipantDto.dates,
            desiredInterview = desiredInterview,
            active = desiredInterview > participantEntity.matchedInterview,
            matchedInterview = if (isRejected) participantEntity.matchedInterview - 1 else participantEntity.matchedInterview
        )

        participantRepository.save(updatedParticipant)
    }

    fun isParticipantRequestExist(participant: ParticipantDto) = participantRepository.exist(
        participant.participantId,
        participant.specialization,
        masteryLevelMapper.masteryLevelToInt(participant.masteryLevel),
        participant.type
    )
}