package org.ratifire.matcherservice.dto

import org.ratifire.matcherservice.enums.MasteryLevel
import org.ratifire.matcherservice.enums.ParticipantType
import java.util.*

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