package org.ratifire.matcherservice.controller

import org.bson.types.ObjectId
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.service.ParticipantService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.format.annotation.DateTimeFormat
import java.util.Date

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
    fun rejectInterview(@PathVariable id: ObjectId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) date: Date) {
        participantService.updateRejected(id, date)
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: ObjectId, @RequestParam desiredInterview: Int, @RequestBody dates: Set<Date>) {
        participantService.update(id, desiredInterview, dates)
    }
}