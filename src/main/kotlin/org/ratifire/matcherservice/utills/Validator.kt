package org.ratifire.matcherservice.utills

import org.ratifire.matcherservice.dto.ParticipantDto


fun validateParticipant(participant: ParticipantDto) = participant.dates.size >= participant.desiredInterview
