package com.agency.properties.mapper

import com.agency.properties.dto.PropertyTypeDTO
import com.agency.properties.entity.PropertyType
import org.springframework.stereotype.Service

@Service
class PropertyTypeMapper {

    fun mapToDto(propertyType: PropertyType): PropertyTypeDTO {
        return PropertyTypeDTO(
                propertyTypeId = propertyType.propertyTypeId,
                propertyTypeName = propertyType.propertyTypeName,
                propertyTypeDescription = propertyType.propertyTypeDescription
        )
    }

    fun mapToEntity(propertyTypeDTO: PropertyTypeDTO): PropertyType {
        return PropertyType(
                propertyTypeId = propertyTypeDTO.propertyTypeId,
                propertyTypeName = propertyTypeDTO.propertyTypeName,
                propertyTypeDescription = propertyTypeDTO.propertyTypeDescription
        )
    }

    fun mapListToDto(types: Iterable<PropertyType>): List<PropertyTypeDTO> {
        return types.map { mapToDto(it) }
    }

    fun mapListToEntity(types: Iterable<PropertyTypeDTO>): List<PropertyType> {
        return types.map { mapToEntity(it) }
    }
}