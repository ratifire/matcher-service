package org.ratifire.matcherservice.service

import org.ratifire.matcherservice.converter.MasteryLeveLMapper
import org.ratifire.matcherservice.converter.ParticipantMapper
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.repository.ParticipantRepository
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

    fun isParticipantRequestExist(participant: ParticipantDto) = participantRepository.exist(
            participant.participantId,
            participant.specialization,
            masteryLevelMapper.masteryLevelToInt(participant.masteryLevel),
            participant.type
        )
}