
//import io.mockk.every
//import io.mockk.mockk
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.Test
//import org.ratifire.matcherservice.entity.ParticipantEntity
//import org.ratifire.matcherservice.enums.ParticipantType
//import org.ratifire.matcherservice.repository.custom.CustomParticipantRepositoryImp
//import org.ratifire.matcherservice.service.MatchingService
//import java.util.Date

class MatchingServiceTest {
//
//    private val participantRepository: CustomParticipantRepositoryImp = mockk()
//    private val matchingService = MatchingService(participantRepository)
//
//    @Test
//    fun findMatchTest() {
//        // Arrange
//        val participant = ParticipantEntity(
//            participantId = 1,
//            type = ParticipantType.INTERVIEWER,
//            desiredInterview = 5,
//            specialization = "Software Engineering",
//            active = true,
//            mastery = "Expert",
//            hardSkills = setOf("Kotlin", "Spring", "Docker"),
//            softSkills = setOf("Communication", "Teamwork"),
//            dates = setOf(
//                Date(1633046400000L), // Oct 1, 2021
//                Date(1633132800000L)  // Oct 2, 2021
//            )
//        )
//
//        val otherParticipants = listOf(
//            ParticipantEntity(
//                participantId = 2,
//                type = ParticipantType.CANDIDATE,
//                desiredInterview = 3,
//                specialization = "Software Engineering",
//                active = true,
//                mastery = "Intermediate",
//                hardSkills = setOf("Kotlin", "Docker", "AWS"),
//                softSkills = setOf("Problem Solving"),
//                dates = setOf(
//                    Date(1633046400000L) // Oct 1, 2021
//                )
//            ),
//            ParticipantEntity(
//                participantId = 3,
//                type = ParticipantType.CANDIDATE,
//                desiredInterview = 2,
//                specialization = "Software Engineering",
//                active = true,
//                mastery = "Beginner",
//                hardSkills = setOf("Java", "Spring"),
//                softSkills = setOf("Leadership"),
//                dates = setOf(
//                    Date(1633225600000L) // Oct 3, 2021
//                )
//            ),
//            ParticipantEntity(
//                participantId = 4,
//                type = ParticipantType.CANDIDATE,
//                desiredInterview = 4,
//                specialization = "Software Engineering",
//                active = true,
//                mastery = "Advanced",
//                hardSkills = setOf("Kotlin", "Spring", "Docker", "Kubernetes"),
//                softSkills = setOf("Adaptability"),
//                dates = setOf(
//                    Date(1633225600000L), // Oct 1, 2021
//                    Date(1633132800000L)  // Oct 2, 2021
//                )
//            )
//        )
//
//        every {
//            participantRepository.findCandidates(
//                participantId = participant.participantId,
//                type = participant.type,
//                specialization = participant.specialization,
//                active = true
//            )
//        } returns otherParticipants
//
//        // Act
//        val matches = matchingService.findMatch(participant)
//        // Assert
//        // Expected matches after filtering participants with overlapping dates
//        val expectedMatches = mapOf(
//            otherParticipants[0] to Date(1633046400000L), // Participant with ID 2
//            otherParticipants[2] to
//              //  Date(1633046400000L),
//                Date(1633132800000L)
//            ) // Participant with ID 4
//
//        // Since the map is sorted by the number of overlapping hard skills in descending order,
//        // Participant ID 4 should come before Participant ID 2
//
//        val expectedOrder = listOf(
//            otherParticipants[2], // Participant with ID 4 (3 overlapping hard skills)
//            otherParticipants[0], // Participant with ID 2 (2 overlapping hard skills)
//        )
//       matches.forEach(::println)
//        assertEquals(2, matches.size)
//        assertEquals(expectedOrder, matches.keys.toList())
//        assertEquals(expectedMatches[otherParticipants[2]], matches[otherParticipants[2]])
//        assertEquals(expectedMatches[otherParticipants[0]], matches[otherParticipants[0]])
//    }
}
