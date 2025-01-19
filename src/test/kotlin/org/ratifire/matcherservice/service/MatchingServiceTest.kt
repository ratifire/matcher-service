//import com.fasterxml.jackson.databind.ObjectMapper
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.fasterxml.jackson.module.kotlin.readValue
//import io.mockk.*
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import org.ratifire.matcherservice.entity.ParticipantEntity
//import org.ratifire.matcherservice.repository.ParticipantRepository
//import org.ratifire.matcherservice.sender.ParticipantSender
//import org.ratifire.matcherservice.service.MatchingService
//import org.ratifire.matcherservice.service.ParticipantService
//import org.ratifire.matcherservice.testUtills.getParticipantEntity
//import java.io.File
//import java.time.Instant
//import java.util.Date
//
//class MatchingServiceTest {
//
//    private val participantRepository: ParticipantRepository = mockk()
//    private val participantService: ParticipantService = mockk()
//    private val participantSender = mockk<ParticipantSender>()
//    private val matchingService = MatchingService(
//        participantRepository, participantService, participantSender
//    )
//
//    private val objectMapper: ObjectMapper = jacksonObjectMapper()
//    private val interviewerFilePath: String = "src/test/resources/participants-interviewer.json"
//
//    @Test
//    fun findMatchTest() {
//        val interviewers = objectMapper.readValue<List<ParticipantEntity>>(File(interviewerFilePath))
//
//        val dates = arrayOf(
//            "2024-11-01T09:00:00Z", "2024-11-02T09:00:00Z", "2024-11-03T09:00:00Z", "2024-11-05T09:00:00Z"
//        ).map { Date.from(Instant.parse(it)) }.toSet()
//
//        val participant = getParticipantEntity(dates)
//
//        every {
//            participantRepository.findCandidates(participant)
//        } returns interviewers
//
//        val matches = matchingService.findMatch(participant)
//        assertEquals(matches.size, participant.desiredInterview)
//    }
//
//    @Test
//    fun matchParticipantTest() {
//
//        val interviewers = objectMapper.readValue<List<ParticipantEntity>>(File(interviewerFilePath))
//
//        val dates = arrayOf(
//            "2024-11-01T09:00:00Z", "2024-11-02T09:00:00Z", "2024-11-03T09:00:00Z", "2024-11-05T09:00:00Z"
//        ).map { Date.from(Instant.parse(it)) }.toSet()
//
//        val participant = getParticipantEntity(dates)
//        val mock = mockk<ParticipantEntity>()
//
//        every {
//            participantRepository.findCandidates(participant)
//        } returns interviewers
//
//        every { participantSender.sendMatchedInterviewParticipants(any()) } just runs
//        every { participantService.save(any<ParticipantEntity>()) } returns mock
//
//        matchingService.matchParticipant(participant)
//
//        verify(exactly = 3) { participantSender.sendMatchedInterviewParticipants(any()) }
//        verify(exactly = 4) { participantService.save(any<ParticipantEntity>()) }
//    }
//}
