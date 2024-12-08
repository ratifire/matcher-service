package org.ratifire.matcherservice.dto

import org.bson.types.ObjectId
import org.ratifire.matcherservice.enums.MasteryLevel
import org.ratifire.matcherservice.enums.ParticipantType
import org.ratifire.matcherservice.enums.UpdateAction
import java.util.Date

data class ParticipantDto(
    val participantId: Int,
    val desiredInterview: Int,
    val type: ParticipantType,
    val specialization: String,
    val masteryLevel: MasteryLevel,
    val hardSkills: Set<String>,
    val softSkills: Set<String>,
    val dates: Set<Date>,
    val averageMark: Double,
    val blackList: Set<Int>,
)

data class PairedParticipantDto(
    val interviewerId: Int,
    val candidateId: Int,
    val interviewerParticipantId: ObjectId,
    val candidateParticipantId: ObjectId,
    val date: Date
)

data class UpdateRequestDto(
    val desiredInterview: Int,
    val dates: Set<Date>,
    val action: UpdateAction
)