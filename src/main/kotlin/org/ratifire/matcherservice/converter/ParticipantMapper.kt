package org.ratifire.matcherservice.converter

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.entity.ParticipantEntity

@Mapper(componentModel = "spring")
interface ParticipantMapper {

    @Mapping(target = "active", constant = "true")
    fun toEntity(dto: ParticipantDto): ParticipantEntity
}