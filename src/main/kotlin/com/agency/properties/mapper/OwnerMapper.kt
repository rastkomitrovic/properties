package com.agency.properties.mapper

import com.agency.properties.dto.OwnerDTO
import com.agency.properties.entity.Owner
import com.agency.properties.util.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OwnerMapper @Autowired constructor(
        private val locationMapper: LocationMapper,
        private val agentMapper: AgentMapper
) {

    fun mapToDto(owner: Owner): OwnerDTO {
        return OwnerDTO(
                ownerId = owner.ownerId,
                ownerFirstName = owner.ownerFirstName,
                ownerLastName = owner.ownerLastName,
                ownerEmail = owner.ownerEmail,
                ownerPhoneNumber = owner.ownerPhoneNumber,
                ownerAddress = owner.ownerPhoneNumber,
                ownerDescription = owner.ownerDescription,
                addedByAgent = if (Utils.isLazyEntityInitialized(owner.addedByAgent)) owner.addedByAgent?.let { agentMapper.mapToDto(it) } else null,
                location = if (Utils.isLazyEntityInitialized(owner.location)) owner.location?.let { locationMapper.mapToDto(it) } else null
        )
    }

    fun mapToEntity(ownerDTO: OwnerDTO): Owner {
        return Owner(
                ownerId = ownerDTO.ownerId,
                ownerFirstName = ownerDTO.ownerFirstName,
                ownerLastName = ownerDTO.ownerLastName,
                ownerEmail = ownerDTO.ownerEmail,
                ownerPhoneNumber = ownerDTO.ownerPhoneNumber,
                ownerAddress = ownerDTO.ownerAddress,
                ownerDescription = ownerDTO.ownerDescription,
                addedByAgent = ownerDTO.addedByAgent?.let { agentMapper.mapToEntity(it) },
                location = ownerDTO.location?.let { locationMapper.mapToEntity(it) }
        )
    }

    fun mapListToDto(owners: Iterable<Owner>): List<OwnerDTO> {
        return owners.map { mapToDto(it) }
    }

    fun mapListToEntity(owners: Iterable<OwnerDTO>): List<Owner> {
        return owners.map { mapToEntity(it) }
    }
}