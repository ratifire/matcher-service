package org.ratifire.matcherservice.service

import org.bson.types.ObjectId
import org.ratifire.matcherservice.converter.MasteryLeveLMapper
import org.ratifire.matcherservice.converter.ParticipantMapper
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.repository.ParticipantRepository
import org.springframework.stereotype.Service
import java.util.Date
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

    fun updateRejected(id: ObjectId, date: Date) {
        participantRepository.findById(id)
            .map { participant ->
                participant.copy(
                    dates = participant.dates + date,
                    active = participant.desiredInterview > participant.matchedInterview - 1,
                    matchedInterview = participant.matchedInterview - 1
                )
            }
            .ifPresentOrElse(
                { updatedParticipant -> participantRepository.save(updatedParticipant) },
                { throw NoSuchElementException("Participant with id: $id not found") }
            )
    }

    fun update(id: ObjectId, desiredInterview: Int, dates: Set<Date>) {
        participantRepository.findById(id)
            .map { participant ->
                participant.copy(
                    dates = dates,
                    desiredInterview = desiredInterview,
                    active = desiredInterview > participant.matchedInterview,
                )
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