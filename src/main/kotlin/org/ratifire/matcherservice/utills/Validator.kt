package org.ratifire.matcherservice.utills

import org.ratifire.matcherservice.dto.ParticipantDto
import java.util.Date

fun validateParticipant(participant: ParticipantDto) = participant.dates.size >= participant.desiredInterview

fun validateParticipant(availableDates: Set<Date>, desiredInterview: Int) = availableDates.size >= desiredInterview
