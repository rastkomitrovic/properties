package com.agency.properties.mapper

import com.agency.properties.dto.PropertyMediaDTO
import com.agency.properties.entity.PropertyMedia
import com.agency.properties.util.MediaType
import org.springframework.stereotype.Service

@Service
class PropertyMediaMapper {

    fun mapToDto(propertyMedia: PropertyMedia): PropertyMediaDTO {
        return PropertyMediaDTO(
                propertyMediaId = propertyMedia.propertyMediaId,
                propertyMediaType = MediaType.valueOf(propertyMedia.propertyMediaType),
                propertyMediaUrl = propertyMedia.propertyMediaUrl
        )
    }

    fun mapToEntity(propertyMediaDTO: PropertyMediaDTO): PropertyMedia {
        return PropertyMedia(
                propertyMediaId = propertyMediaDTO.propertyMediaId,
                propertyMediaType = propertyMediaDTO.propertyMediaType.toString(),
                propertyMediaUrl = propertyMediaDTO.propertyMediaUrl
        )
    }

    fun mapListToDto(list: Iterable<PropertyMedia>): List<PropertyMediaDTO> {
        return list.map { mapToDto(it) }
    }

    fun mapListToEntity(list: Iterable<PropertyMediaDTO>): List<PropertyMedia> {
        return list.map { mapToEntity(it) }
    }
}