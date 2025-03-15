package org.ratifire.matcherservice.repository.custom

import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.enums.ParticipantType
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

interface CustomParticipantRepository {

    fun findCandidates(participant: ParticipantEntity): List<ParticipantEntity>
    fun exist(participantId: Long, specialization: String, mastery: Int, type: ParticipantType): Boolean
}

class CustomParticipantRepositoryImpl(private val mongoTemplate: MongoTemplate) : CustomParticipantRepository {

    override fun findCandidates(participant: ParticipantEntity): List<ParticipantEntity> {

        val masteryAndMarks = when (participant.type) {
            ParticipantType.CANDIDATE -> {
                Criteria().orOperator(
                    Criteria.where("masteryLevel").gte(participant.masteryLevel), Criteria().andOperator(
                        Criteria.where("masteryLevel").`is`(participant.masteryLevel),
                        Criteria.where("averageMark").gte(participant.averageMark)
                    )
                )
            }

            ParticipantType.INTERVIEWER -> {
                Criteria().orOperator(
                    Criteria.where("masteryLevel").lt(participant.masteryLevel), Criteria().andOperator(
                        Criteria.where("masteryLevel").`is`(participant.masteryLevel),
                        Criteria.where("averageMark").lt(participant.averageMark)
                    )
                )
            }
        }
        val participantIdCriteria =
            Criteria.where("participantId").ne(participant.participantId).nin(participant.blackList)

        val query = Query().apply {
            addCriteria(Criteria.where("type").ne(participant.type))
            addCriteria(Criteria.where("specialization").`is`(participant.specialization))
            addCriteria(Criteria.where("active").`is`(true))
            addCriteria(Criteria.where("dates").`in`(participant.dates))
            addCriteria(Criteria.where("blackList").ne(participant.participantId))
            addCriteria(participantIdCriteria)
            addCriteria(masteryAndMarks)
        }
        return mongoTemplate.find(query, ParticipantEntity::class.java)
    }

    override fun exist(participantId: Long, specialization: String, mastery: Int, type: ParticipantType): Boolean {
        val query = Query().apply {
            addCriteria(Criteria.where("participantId").`is`(participantId))
            addCriteria(Criteria.where("specialization").`is`(specialization))
            addCriteria(Criteria.where("masteryLevel").`is`(mastery))
            addCriteria(Criteria.where("type").`is`(type))
        }
        return mongoTemplate.exists(query, ParticipantEntity::class.java)
    }
}