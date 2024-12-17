package org.ratifire.matcherservice.service

import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.PromptTemplate
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.stereotype.Service

@Service
class OpenAiService(private val chatModel: OpenAiChatModel) {


    fun matchCandidateWithInterviewers(candidate: ParticipantEntity, interviewers: List<ParticipantEntity>) {
        // Build the chat messages
        val messages = buildMessages(candidate, interviewers)
        // Call the model
        val response = chatModel.call(messages)

        // Extract the response content from the assistant
        val responseContent = response.result.output.content


        println(responseContent)
    }

    private fun buildMessages(candidate: ParticipantEntity, interviewers: List<ParticipantEntity>): Prompt {

        val head = PromptTemplate(
            """
                You are an AI that compares a candidate's hard and soft skills with multiple interviewers.
                Consider approximate matches (e.g., "Java Core" ≈ "Java Basic").
                Return a JSON array where each element is:
                {
                  "interviewerId": <int>,
                  "matchedPercentage": <numeric>
                }
            """.trimIndent()
        ).createMessage()

        val candidateInfo = PromptTemplate("""
            Candidate (ID: ${candidate.participantId}):
            Hard Skills: ${candidate.hardSkills.joinToString(", ")}
            Soft Skills: ${candidate.softSkills.joinToString(", ")}
        """.trimIndent()).createMessage()

        val interviewersInfo = PromptTemplate(interviewers.joinToString("\n") { interviewer ->
            """
            Interviewer (ID: ${interviewer.participantId}):
            Hard Skills: ${interviewer.hardSkills.joinToString(", ")}
            Soft Skills: ${interviewer.softSkills.joinToString(", ")}
            """.trimIndent()

        }).createMessage()

        return Prompt(listOf(head, candidateInfo, interviewersInfo))

    }
}