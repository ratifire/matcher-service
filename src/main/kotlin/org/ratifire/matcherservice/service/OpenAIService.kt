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
        val headTemplate = PromptTemplate(
            """
    You are an AI that compares a candidate's hard and soft skills with multiple interviewers.
    Consider approximate matches (e.g., "Java Core" ≈ "Java Basic"). Language Ukrainian 
    Return a JSON array where each element is:
    {{
      "interviewerId": "int",
      "matchedPercentage": "numeric",
      "description": "String"
    }}
    """.trimIndent()
        )
        val head = headTemplate.createMessage()

// Candidate information template
        val candidateTemplate = PromptTemplate(
            """
    Candidate (ID: {candidateId}):
    Hard Skills: {hardSkills}
    Soft Skills: {softSkills}
    """.trimIndent()
        )
        val candidateInfo = candidateTemplate.createMessage(
            mapOf(
                "candidateId" to candidate.participantId,
                "hardSkills" to candidate.hardSkills.joinToString(", "),
                "softSkills" to candidate.softSkills.joinToString(", ")
            )
        )

// Preprocess interviewers information outside the template
        val interviewersString = interviewers.joinToString("\n") { interviewer ->
            """
    Interviewer (ID: ${interviewer.participantId}):
    Hard Skills: ${interviewer.hardSkills.joinToString(", ")}
    Soft Skills: ${interviewer.softSkills.joinToString(", ")}
    """.trimIndent()
        }

// Interviewers template
        val interviewersTemplate = PromptTemplate(
            """
    {interviewersInfo}
    """.trimIndent()
        )
        val interviewersInfo = interviewersTemplate.createMessage(
            mapOf("interviewersInfo" to interviewersString)
        )

// Combine all messages into a single prompt
        return Prompt(listOf(head, candidateInfo, interviewersInfo))
    }
}