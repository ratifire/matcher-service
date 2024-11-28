package org.ratifire.matcherservice.dto

import java.util.Date

data class PairedParticipantDto(
    val interviewerId: Int,
    val candidateId: Int,
    val date : Date
)
