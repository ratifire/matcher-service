package org.ratifire.matcherservice.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.ratifire.matcherservice.converter.MasteryLeveLMapper
import org.ratifire.matcherservice.converter.ParticipantMapper
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.repository.ParticipantRepository
import org.ratifire.matcherservice.testUtills.getParticipantDto
import org.ratifire.matcherservice.testUtills.getParticipantEntity
import java.time.Instant
import java.util.*

class ParticipantServiceTest {

   private val participantRepository = mockk<ParticipantRepository>()
    private val participantMapper = mockk<ParticipantMapper>()
    private val participantService = ParticipantService(participantRepository, participantMapper, MasteryLeveLMapper)

    @Test
    fun saveTest() {

        val dates = arrayOf("2024-11-01T09:00:00Z")
            .map { Date.from(Instant.parse(it)) }
            .toSet()

        val participant =  getParticipantDto(dates)
        val mock =  mockk<ParticipantEntity>()

        every {
            participantRepository.save(any<ParticipantEntity>())
        } returns mock

        every {
            participantMapper.toEntity(any<ParticipantDto>())
        } returns mock

        participantService.save(participant)

        verify(exactly = 1) { participantRepository.save(any<ParticipantEntity>()) }
        verify(exactly = 1) { participantMapper.toEntity(any<ParticipantDto>()) }

    }

    @Test
    fun saveEntityTest() {

        val dates = arrayOf("2024-11-01T09:00:00Z")
            .map { Date.from(Instant.parse(it)) }
            .toSet()

        val participant =  getParticipantEntity(dates)

        every {
            participantRepository.save(any<ParticipantEntity>())
        } returns participant
        participantService.save(participant)

        verify(exactly = 1) { participantRepository.save(any<ParticipantEntity>()) }

    }
    @Test
    fun isParticipantRequestExistTest() {

        val dates = arrayOf("2024-11-01T09:00:00Z")
            .map { Date.from(Instant.parse(it)) }
            .toSet()

        val participant =  getParticipantDto(dates)

        every {
            participantRepository.exist(any(), any(),any(),any())
        } returns true

        participantService.isParticipantRequestExist(participant)

        verify(exactly = 1) { participantRepository.exist(any(), any(),any(),any()) }

    }

}