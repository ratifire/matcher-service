package org.ratifire.matcherservice.repository

import org.bson.types.ObjectId
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.repository.custom.CustomParticipantRepository
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface ParticipantRepository : CrudRepository<ParticipantEntity, ObjectId>, CustomParticipantRepository {
    fun findByCoreRequestId(coreRequestId: Long): Optional<ParticipantEntity>?
}