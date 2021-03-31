package com.agency.properties.mapper

import com.agency.properties.dto.PropertyAttributeDTO
import com.agency.properties.entity.PropertyAttribute
import org.springframework.stereotype.Service

@Service
class PropertyAttributeMapper {

    fun mapToDto(propertyAttribute: PropertyAttribute): PropertyAttributeDTO {
        return PropertyAttributeDTO(
                propertyAttributeId = propertyAttribute.propertyAttributeId,
                propertyAttributeName = propertyAttribute.propertyAttributeName,
                propertyAttributeDescription = propertyAttribute.propertyAttributeDescription
        )
    }

    fun mapToEntity(propertyAttributeDTO: PropertyAttributeDTO): PropertyAttribute {
        return PropertyAttribute(
                propertyAttributeId = propertyAttributeDTO.propertyAttributeId,
                propertyAttributeName = propertyAttributeDTO.propertyAttributeName,
                propertyAttributeDescription = propertyAttributeDTO.propertyAttributeDescription
        )
    }

    fun mapListToDto(attributes: Iterable<PropertyAttribute>): List<PropertyAttributeDTO> {
        return attributes.map { mapToDto(it) }
    }

    fun mapListToEntity(attributes: Iterable<PropertyAttributeDTO>): List<PropertyAttribute> {
        return attributes.map { mapToEntity(it) }
    }
}