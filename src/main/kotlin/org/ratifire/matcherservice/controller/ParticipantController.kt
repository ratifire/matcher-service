package org.ratifire.matcherservice.controller

import org.bson.types.ObjectId
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.dto.UpdateRequestDto
import org.ratifire.matcherservice.service.ParticipantService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping

@RestController
@RequestMapping("/participants")
class ParticipantController(
    val participantService: ParticipantService
) {

    @PostMapping
    fun save(@RequestBody participant: ParticipantDto) {
        participantService.save(participant)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: ObjectId) {
        participantService.delete(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: UpdateRequestDto) =
        participantService.update(id, request)
}