package org.ratifire.matcherservice.dto

import org.bson.types.ObjectId
import org.ratifire.matcherservice.enums.ParticipantType
import java.util.Date

data class ParticipantDto(
    val participantId: Long,
    val coreRequestId: Long,
    val desiredInterview: Int,
    val type: ParticipantType,
    val specialization: String,
    val masteryLevel: Int,
    val hardSkills: Set<String>,
    val softSkills: Set<String>,
    val dates: Set<Date>,
    val averageMark: Double,
    val blackList: Set<Int>,
)

data class PairedParticipantDto(
    val interviewerId: Long,
    val candidateId: Long,
    val interviewerParticipantId: ObjectId,
    val candidateParticipantId: ObjectId,
    val date: Date
)

data class UpdateRequestDto(
    val desiredInterview: Int,
    val matchedInterview: Int,
    val dates: Set<Date>
)