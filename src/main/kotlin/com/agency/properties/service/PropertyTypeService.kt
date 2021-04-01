package com.agency.properties.service

import com.agency.properties.dto.PropertyTypeDTO
import com.agency.properties.mapper.PropertyTypeMapper
import com.agency.properties.repository.PropertyTypeRepository
import com.agency.properties.util.AgencyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class PropertyTypeService @Autowired constructor(
        private val propertyTypeRepository: PropertyTypeRepository,
        private val propertyTypeMapper: PropertyTypeMapper
) {

    fun savePropertyType(propertyTypeDTO: PropertyTypeDTO): PropertyTypeDTO {
        if (propertyTypeDTO.propertyTypeId != null && propertyTypeRepository.existsById(propertyTypeDTO.propertyTypeId))
            throw AgencyException("Property type with the passed id:${propertyTypeDTO.propertyTypeId} already exist!")
        return propertyTypeMapper.mapToDto(propertyTypeRepository.save(propertyTypeMapper.mapToEntity(propertyTypeDTO)))
    }

    fun updatePropertyType(propertyTypeDTO: PropertyTypeDTO): PropertyTypeDTO {
        if (propertyTypeDTO.propertyTypeId != null && !propertyTypeRepository.existsById(propertyTypeDTO.propertyTypeId))
            throw AgencyException("Property type with the passed id:${propertyTypeDTO.propertyTypeId} doesn't exist!")
        return propertyTypeMapper.mapToDto(propertyTypeRepository.save(propertyTypeMapper.mapToEntity(propertyTypeDTO)))
    }

    fun deletePropertyTypeById(id: Long) {
        if (!propertyTypeRepository.existsById(id))
            throw AgencyException("Property type with the passed id:${id} doesn't exist!")
        propertyTypeRepository.deleteById(id)
    }

    fun findAll(): List<PropertyTypeDTO> {
        return propertyTypeMapper.mapListToDto(propertyTypeRepository.findAll())
    }

    fun saveAll(list: List<PropertyTypeDTO>): List<PropertyTypeDTO> {
        list.forEach { if (it.propertyTypeId != null && propertyTypeRepository.existsById(it.propertyTypeId)) throw AgencyException("Property type with the passed id:${it.propertyTypeId} already exist!") }
        return propertyTypeMapper.mapListToDto(propertyTypeRepository.saveAll(propertyTypeMapper.mapListToEntity(list)))
    }
}