package org.ratifire.matcherservice.repository

import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.repository.custom.CustomParticipantRepository
import org.springframework.data.repository.CrudRepository

interface ParticipantRepository : CrudRepository<ParticipantEntity, Int>, CustomParticipantRepository {
}