//package org.ratifire.matcherservice.service
//
//import io.mockk.every
//import io.mockk.mockk
//import io.mockk.verify
//import org.bson.types.ObjectId
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.assertThrows
//import org.ratifire.matcherservice.converter.ParticipantMapper
//import org.ratifire.matcherservice.dto.ParticipantDto
//import org.ratifire.matcherservice.entity.ParticipantEntity
//import org.ratifire.matcherservice.repository.ParticipantRepository
//import org.ratifire.matcherservice.testUtills.getParticipantDto
//import org.ratifire.matcherservice.testUtills.getParticipantEntity
//import org.ratifire.matcherservice.testUtills.getUpdateRequestDto
//import java.time.Instant
//import java.util.Date
//import java.util.Optional
//
//class ParticipantServiceTest {
//
//    private val participantRepository = mockk<ParticipantRepository>()
//    private val participantMapper = mockk<ParticipantMapper>()
//    private val participantService = ParticipantService(participantRepository, participantMapper)
//    private var participantId = ObjectId("64b7e9f4d92fbc32ef123456")
//
//    @Test
//    fun saveTest() {
//
//        val dates = arrayOf("2024-11-01T09:00:00Z").map { Date.from(Instant.parse(it)) }.toSet()
//
//        val participant = getParticipantDto(dates)
//        val mock = mockk<ParticipantEntity>()
//
//        every {
//            participantRepository.save(any<ParticipantEntity>())
//        } returns mock
//
//        every {
//            participantMapper.toEntity(any<ParticipantDto>())
//        } returns mock
//
//        participantService.save(participant)
//
//        verify(exactly = 1) { participantRepository.save(any<ParticipantEntity>()) }
//        verify(exactly = 1) { participantMapper.toEntity(any<ParticipantDto>()) }
//    }
//
//    @Test
//    fun saveEntityTest() {
//
//        val dates = arrayOf("2024-11-01T09:00:00Z").map { Date.from(Instant.parse(it)) }.toSet()
//
//        val participant = getParticipantEntity(dates)
//
//        every {
//            participantRepository.save(any<ParticipantEntity>())
//        } returns participant
//        participantService.save(participant)
//
//        verify(exactly = 1) { participantRepository.save(any<ParticipantEntity>()) }
//    }
//
//    @Test
//    fun isParticipantRequestExistTest() {
//
//        val dates = arrayOf("2024-11-01T09:00:00Z").map { Date.from(Instant.parse(it)) }.toSet()
//
//        val participant = getParticipantDto(dates)
//
//        every {
//            participantRepository.exist(any(), any(), any(), any())
//        } returns true
//
//        participantService.isParticipantRequestExist(participant)
//
//        verify(exactly = 1) { participantRepository.exist(any(), any(), any(), any()) }
//    }
//
//    @Test
//    fun deleteParticipantSuccessTest() {
//        every { participantRepository.deleteById(participantId) } returns Unit
//
//        participantService.delete(participantId)
//
//        verify(exactly = 1) { participantRepository.deleteById(participantId) }
//    }
//
//    @Test
//    fun updateSuccessTest() {
//        val dates = arrayOf("2024-11-02T09:00:00Z").map { Date.from(Instant.parse(it)) }.toSet()
//        val existingParticipant = getParticipantEntity(dates)
//        val request = getUpdateRequestDto(3, 2)
//
//        every { participantRepository.findById(participantId) } returns Optional.of(existingParticipant)
//        every { participantRepository.save(any()) } answers { firstArg() }
//
//        participantService.update(participantId, request)
//
//        verify {
//            participantRepository.save(match { updatedParticipant ->
//                updatedParticipant.dates == request.dates &&
//                        updatedParticipant.desiredInterview == request.desiredInterview &&
//                        updatedParticipant.matchedInterview == request.matchedInterview &&
//                        updatedParticipant.active
//            })
//        }
//    }
//
//    @Test
//    fun updateThrowExceptionWhenParticipantNotFound() {
//        val updateRequest = getUpdateRequestDto()
//
//        every { participantRepository.findById(participantId) } returns Optional.empty()
//
//        assertThrows<NoSuchElementException> {
//            participantService.update(participantId, updateRequest)
//        }
//
//        verify(exactly = 0) { participantRepository.save(any<ParticipantEntity>()) }
//    }
//}