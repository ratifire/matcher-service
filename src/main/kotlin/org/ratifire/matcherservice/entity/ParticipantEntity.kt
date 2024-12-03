package org.ratifire.matcherservice.entity

import org.bson.types.ObjectId
import org.ratifire.matcherservice.enums.ParticipantType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("Participant")
data class ParticipantEntity(
    @Id val id: ObjectId? = null,
    val participantId: Int,
    val specialization: String,
    val type: ParticipantType,
    val masteryLevel: Int,
    val desiredInterview: Int,
    val matchedInterview: Int = 0,
    val active: Boolean,
    val hardSkills: Set<String>,
    val softSkills: Set<String>,
    val dates: Set<Date>,
    val averageMark: Double,
    val blackList: Set<Int>
)
