package org.ratifire.matcherservice.utills

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.enums.MasteryLevel
import org.ratifire.matcherservice.enums.ParticipantType
import java.time.Instant
import java.util.*

class ValidatorTest {

    private val participant: ParticipantDto = ParticipantDto(
        dates = emptySet(),
        desiredInterview = 4,
        hardSkills = emptySet(),
        softSkills = emptySet(),
        blackList = emptySet(),
        participantId = 1,
        type = ParticipantType.CANDIDATE,
        specialization = "DEV",
        masteryLevel = MasteryLevel.JUNIOR.level,
        averageMark = 3.2,
    )


    @Test
    fun validateParticipantPositiveTest() {

        val dates = arrayOf(
            "2024-11-01T09:00:00Z", "2024-11-02T09:00:00Z", "2024-11-03T09:00:00Z", "2024-11-05T09:00:00Z"
        ).map { Date.from(Instant.parse(it)) }.toSet()

        val participantDto = participant.copy(dates = dates)
        Assertions.assertTrue(validateParticipant(participantDto))
    }

    @Test
    fun validateParticipantNegativeTest() {

        val dates = arrayOf(
            "2024-11-01T09:00:00Z",
        ).map { Date.from(Instant.parse(it)) }.toSet()

        val participantDto = participant.copy(dates = dates, desiredInterview = 10)
        Assertions.assertFalse(validateParticipant(participantDto))
    }
}