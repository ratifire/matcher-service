package org.ratifire.matcherservice.converter

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.ratifire.matcherservice.dto.ParticipantDto
import org.ratifire.matcherservice.entity.ParticipantEntity
import org.ratifire.matcherservice.enums.MasteryLevel
import org.springframework.stereotype.Component

@Mapper(componentModel = "spring", uses = [MasteryLeveLMapper::class])
interface ParticipantMapper {

    @Mapping(target = "active", constant = "true")
    @Mapping(source = "masteryLevel", target = "masteryLevel", qualifiedByName = ["masteryLevelToInt"])
    fun toEntity(dto: ParticipantDto): ParticipantEntity

    @Mapping(source = "masteryLevel", target = "masteryLevel", qualifiedByName = ["intToMasteryLevel"])
    fun fromEntity(dto: ParticipantEntity): ParticipantDto
}

@Component
object MasteryLeveLMapper {

    @Named("masteryLevelToInt")
    fun masteryLevelToInt(masteryLevel: MasteryLevel): Int {
        return when (masteryLevel) {
            MasteryLevel.JUNIOR -> 1
            MasteryLevel.MIDDLE -> 2
            MasteryLevel.SENIOR -> 3
        }
    }

    @Named("intToMasteryLevel")
    fun intToMasteryLevel(mastery: Int): MasteryLevel {
        return when (mastery) {
            1 -> MasteryLevel.JUNIOR
            2 -> MasteryLevel.MIDDLE
            3 -> MasteryLevel.SENIOR
            else -> throw IllegalArgumentException("Unknown mastery level: $mastery")
        }
    }
}