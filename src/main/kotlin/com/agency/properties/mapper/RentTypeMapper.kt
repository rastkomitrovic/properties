package com.agency.properties.mapper

import com.agency.properties.dto.RentTypeDTO
import com.agency.properties.entity.RentType
import org.springframework.stereotype.Service

@Service
class RentTypeMapper {

    fun mapToDto(rentType: RentType): RentTypeDTO {
        return RentTypeDTO(
                rentTypeId = rentType.rentTypeId,
                rentTypeName = rentType.rentTypeName,
                rentTypeDescription = rentType.rentTypeDescription
        )
    }

    fun mapToEntity(rentTypeDTO: RentTypeDTO): RentType {
        return RentType(
                rentTypeId = rentTypeDTO.rentTypeId,
                rentTypeName = rentTypeDTO.rentTypeName,
                rentTypeDescription = rentTypeDTO.rentTypeDescription
        )
    }

    fun mapListToDto(rentTypes: Iterable<RentType>): List<RentTypeDTO> {
        return rentTypes.map { mapToDto(it) }
    }

    fun mapListToEntities(rentTypes: Iterable<RentTypeDTO>): List<RentType> {
        return rentTypes.map { mapToEntity(it) }
    }
}