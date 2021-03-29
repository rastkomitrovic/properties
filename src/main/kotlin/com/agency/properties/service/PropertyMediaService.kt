package com.agency.properties.service

import com.agency.properties.dto.PropertyMediaDTO
import com.agency.properties.mapper.PropertyMediaMapper
import com.agency.properties.repository.PropertyMediaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PropertyMediaService @Autowired constructor(
        private val propertyMediaRepository: PropertyMediaRepository,
        private val propertyMediaMapper: PropertyMediaMapper
) {

    fun savePropertyMedia(propertyMediaDTO: PropertyMediaDTO): Optional<PropertyMediaDTO>?{
        if(!propertyMediaRepository.existsById(propertyMediaDTO.propertyMediaId))
            return Optional.of(propertyMediaMapper.mapToDto(propertyMediaRepository.save(propertyMediaMapper.mapToEntity(propertyMediaDTO))))
        return Optional.empty()
    }

    fun deletePropertyMediaById(id:Long){
        if(propertyMediaRepository.existsById(id))
            propertyMediaRepository.deleteById(id)
    }

    fun findPropertyMediaById(id:Long):Optional<PropertyMediaDTO>{
        val propertyMedia = propertyMediaRepository.findById(id)
        return when(propertyMedia.isPresent){
            true -> Optional.of(propertyMediaMapper.mapToDto(propertyMedia.get()))
            else -> Optional.empty()
        }
    }
}