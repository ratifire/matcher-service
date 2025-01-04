package org.ratifire.matcherservice.controller

import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.service.OpenAiService
import org.springframework.ai.chat.client.ChatClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/ai")
class AiTestController(val openAiService: OpenAiService, val chatClient: ChatClient) {

    @PostMapping
    fun postAiTest(@RequestBody participantParty: ParticipantFacade) = openAiService
        .matchCandidateWithInterviewers(participantParty.participant, participantParty.participants)


    class ParticipantFacade(val participant: ParticipantEntity, val participants: List<ParticipantEntity>)


}