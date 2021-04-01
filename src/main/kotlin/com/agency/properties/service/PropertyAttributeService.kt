package com.agency.properties.service

import com.agency.properties.dto.PropertyAttributeDTO
import com.agency.properties.mapper.PropertyAttributeMapper
import com.agency.properties.repository.PropertyAttributeRepository
import com.agency.properties.util.AgencyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class PropertyAttributeService @Autowired constructor(
        private val propertyAttributeRepository: PropertyAttributeRepository,
        private val propertyAttributeMapper: PropertyAttributeMapper
) {

    fun savePropertyAttribute(propertyAttributeDTO: PropertyAttributeDTO): PropertyAttributeDTO {
        if (propertyAttributeDTO.propertyAttributeId != null && propertyAttributeRepository.existsById(propertyAttributeDTO.propertyAttributeId))
            throw AgencyException("Property attribute with the passed id:${propertyAttributeDTO.propertyAttributeId} already exists!")
        return propertyAttributeMapper.mapToDto(propertyAttributeRepository.save(propertyAttributeMapper.mapToEntity(propertyAttributeDTO)))
    }

    fun updatePropertyAttribute(propertyAttributeDTO: PropertyAttributeDTO): PropertyAttributeDTO {
        if (propertyAttributeDTO.propertyAttributeId != null && !propertyAttributeRepository.existsById(propertyAttributeDTO.propertyAttributeId))
            throw AgencyException("Property attribute with the passed id:${propertyAttributeDTO.propertyAttributeId} doesn't exist!")
        return propertyAttributeMapper.mapToDto(propertyAttributeRepository.save(propertyAttributeMapper.mapToEntity(propertyAttributeDTO)))
    }

    fun deletePropertyAttributeById(id: Long) {
        if (!propertyAttributeRepository.existsById(id))
            throw AgencyException("Property attribute with the passed id:${id} doesn't exist!")
        propertyAttributeRepository.deleteById(id)
    }

    fun findAll(): List<PropertyAttributeDTO> {
        return propertyAttributeMapper.mapListToDto(propertyAttributeRepository.findAll())
    }

    fun saveAll(list: List<PropertyAttributeDTO>): List<PropertyAttributeDTO> {
        list.forEach { if (it.propertyAttributeId != null && propertyAttributeRepository.existsById(it.propertyAttributeId)) throw AgencyException("Property attribute with the passed id:${it.propertyAttributeId} already exists!") }
        return propertyAttributeMapper.mapListToDto(propertyAttributeRepository.saveAll(propertyAttributeMapper.mapListToEntity(list)))
    }
}