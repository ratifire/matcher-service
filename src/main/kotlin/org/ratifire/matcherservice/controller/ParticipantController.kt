package org.ratifire.matcherservice.controller

import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.service.ParticipantService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/participant")
class ParticipantController(
    val participantService: ParticipantService
) {

    @PostMapping
    fun save(@RequestBody participant: ParticipantDto){
        participantService.save(participant)
    }
}