package org.ratifire.matcherservice.testUtills

import org.bson.types.ObjectId
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.dto.UpdateRequestDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.enums.MasteryLevel
import org.ratifire.matcherservice.enums.ParticipantType
import java.time.Instant
import java.util.*

fun getParticipantEntity(dates: Set<Date>, desiredInterview: Int = 3) = ParticipantEntity(
    id = ObjectId("64b7e9f4d92fbc32ef123456"),
    participantId = 99,
    type = ParticipantType.CANDIDATE,
    desiredInterview = desiredInterview,
    matchedInterview = 0,
    specialization = "Software Engineering",
    active = true,
    masteryLevel = 1,
    hardSkills = setOf(
        "Java", "Spring", "Hibernate"
    ),
    softSkills = setOf(
        "Analytical Thinking", "Mentoring"
    ),
    dates = dates,
    averageMark = 3.2,
    blackList = setOf(91)
)

fun getParticipantDto(dates: Set<Date>, desiredInterview: Int = 3) = ParticipantDto(
    participantId = 99,
    type = ParticipantType.CANDIDATE,
    desiredInterview = desiredInterview,
    specialization = "Software Engineering",
    masteryLevel = MasteryLevel.SENIOR,
    hardSkills = setOf(
        "Java", "Spring", "Hibernate"
    ),
    softSkills = setOf(
        "Analytical Thinking", "Mentoring"
    ),
    dates = dates,
    averageMark = 3.2,
    blackList = setOf(91)
)

fun getUpdateRequestDto(desiredInterview: Int = 2, matchedInterview: Int = 2) = UpdateRequestDto(
    dates = arrayOf("2024-11-01T09:00:00Z").map { Date.from(Instant.parse(it)) }.toSet(),
    desiredInterview = desiredInterview,
    matchedInterview = matchedInterview
)
