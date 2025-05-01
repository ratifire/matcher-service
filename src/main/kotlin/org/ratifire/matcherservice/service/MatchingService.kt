package org.ratifire.matcherservice.service

import org.ratifire.matcherservice.dto.PairedParticipantDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.enums.ParticipantType
import org.ratifire.matcherservice.repository.ParticipantRepository
import org.ratifire.matcherservice.sender.ParticipantSender
import org.springframework.stereotype.Service
import java.util.Date
import java.util.logging.Logger

@Service
class MatchingService(
    val participantRepository: ParticipantRepository,
    val participantService: ParticipantService,
    val participantSender: ParticipantSender
) {

    private val logger: Logger = Logger.getLogger(MatchingService::class.java.name)

    fun findMatch(participant: ParticipantEntity): Map<ParticipantEntity, Date> {
        val candidates = participantRepository.findCandidates(participant)
            .sortedByDescending { participant.hardSkills.intersect(it.hardSkills).size }


        val availableDate = participant.dates.toMutableSet()
        return candidates.mapNotNull { p ->
            availableDate.find { it in p.dates }?.let {
                availableDate.remove(it)
                p to it
            }
        }.toMap().entries.take(participant.desiredInterview).associate { it.toPair() }
    }

    fun matchParticipant(participant: ParticipantEntity) {
        findMatch(participant).onEach {
            val pairedParticipantDto = when (it.key.type) {
                ParticipantType.INTERVIEWER -> PairedParticipantDto(
                    interviewerId = it.key.participantId,
                    candidateId = participant.participantId,
                    interviewerParticipantId = it.key.id,
                    candidateParticipantId = participant.id,
                    date = it.value
                )
                ParticipantType.CANDIDATE -> PairedParticipantDto(
                    interviewerId = participant.participantId,
                    candidateId = it.key.participantId,
                    interviewerParticipantId = participant.id,
                    candidateParticipantId = it.key.id,
                    date = it.value
                )
            }

            participantSender.sendMatchedInterviewParticipants(pairedParticipantDto)
        }.map {
            participantService.save(
                it.key.copy(
                    dates = it.key.dates - it.value,
                    active = it.key.desiredInterview > it.key.matchedInterview + 1,
                    matchedInterview = it.key.matchedInterview + 1
                )
            )
            it.value
        }.toSet().let {
            participantService.save(
                participant.copy(
                    dates = participant.dates - it,
                    active = participant.desiredInterview > it.size,
                    matchedInterview = it.size
                )
            )
        }
    }
}