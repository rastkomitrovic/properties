package com.agency.properties.service

import com.agency.properties.dto.PropertyMediaDTO
import com.agency.properties.mapper.PropertyMediaMapper
import com.agency.properties.repository.PropertyMediaRepository
import com.agency.properties.util.AgencyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class PropertyMediaService @Autowired constructor(
        private val propertyMediaRepository: PropertyMediaRepository,
        private val propertyMediaMapper: PropertyMediaMapper
) {

    fun savePropertyMedia(propertyMediaDTO: PropertyMediaDTO): PropertyMediaDTO {
        if (propertyMediaDTO.propertyMediaId != null && propertyMediaRepository.existsById(propertyMediaDTO.propertyMediaId))
            throw AgencyException("Property media with the passed id:${propertyMediaDTO.propertyMediaId} already exists!")
        return propertyMediaMapper.mapToDto(propertyMediaRepository.save(propertyMediaMapper.mapToEntity(propertyMediaDTO)))
    }

    fun deletePropertyMediaById(id: Long) {
        if (!propertyMediaRepository.existsById(id))
            throw AgencyException("Property media with the passed id:${id} doesn't exist!")
        propertyMediaRepository.deleteById(id)
    }

    fun findPropertyMediaById(id: Long): Optional<PropertyMediaDTO> {
        val propertyMedia = propertyMediaRepository.findById(id)
        return when (propertyMedia.isPresent) {
            true -> Optional.of(propertyMediaMapper.mapToDto(propertyMedia.get()))
            else -> Optional.empty()
        }
    }

    fun saveAll(list: List<PropertyMediaDTO>): List<PropertyMediaDTO> {
        list.forEach { if (it.propertyMediaId != null && propertyMediaRepository.existsById(it.propertyMediaId)) throw AgencyException("Property media with the passed id:${it.propertyMediaId} already exists!") }
        return propertyMediaMapper.mapListToDto(propertyMediaRepository.saveAll(propertyMediaMapper.mapListToEntity(list)))
    }
}