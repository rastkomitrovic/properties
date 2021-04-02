package com.agency.properties.service

import com.agency.properties.dto.PropertyTypeDTO
import com.agency.properties.mapper.PropertyTypeMapper
import com.agency.properties.repository.PropertyTypeRepository
import com.agency.properties.util.AgencyException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
@CacheConfig(cacheNames = [PropertyTypeService.PROPERTY_TYPES_ALL_CACHE, PropertyTypeService.PROPERTY_TYPES_BY_ID_CACHE])
class PropertyTypeService @Autowired constructor(
        private val propertyTypeRepository: PropertyTypeRepository,
        private val propertyTypeMapper: PropertyTypeMapper
) {

    private val log = LoggerFactory.getLogger(PropertyTypeService::class.java)

    @CacheEvict(value = [PROPERTY_TYPES_ALL_CACHE, PROPERTY_TYPES_BY_ID_CACHE], allEntries = true)
    fun savePropertyType(propertyTypeDTO: PropertyTypeDTO): PropertyTypeDTO {
        if (propertyTypeDTO.propertyTypeId != null && propertyTypeRepository.existsById(propertyTypeDTO.propertyTypeId))
            throw AgencyException("Property type with the passed id:${propertyTypeDTO.propertyTypeId} already exist!")
        val type = propertyTypeMapper.mapToDto(propertyTypeRepository.save(propertyTypeMapper.mapToEntity(propertyTypeDTO)))
        log.info("Evicting from caches: $PROPERTY_TYPES_ALL_CACHE and $PROPERTY_TYPES_BY_ID_CACHE for all entries")
        return type
    }

    @Caching(
            evict = [
                CacheEvict(value = [PROPERTY_TYPES_BY_ID_CACHE], key = "{#propertyTypeDTO.propertyTypeId}"),
                CacheEvict(value = [PROPERTY_TYPES_ALL_CACHE], allEntries = true)
            ]
    )
    fun updatePropertyType(propertyTypeDTO: PropertyTypeDTO): PropertyTypeDTO {
        if (propertyTypeDTO.propertyTypeId != null && !propertyTypeRepository.existsById(propertyTypeDTO.propertyTypeId))
            throw AgencyException("Property type with the passed id:${propertyTypeDTO.propertyTypeId} doesn't exist!")
        val property = propertyTypeMapper.mapToDto(propertyTypeRepository.save(propertyTypeMapper.mapToEntity(propertyTypeDTO)))
        log.info("Evicting from caches: $PROPERTY_TYPES_BY_ID_CACHE for key: ${propertyTypeDTO.propertyTypeId} and from $PROPERTY_TYPES_ALL_CACHE all entries")
        return property
    }

    @Caching(
            evict = [
                CacheEvict(value = [PROPERTY_TYPES_BY_ID_CACHE], key = "{#id}"),
                CacheEvict(value = [PROPERTY_TYPES_ALL_CACHE], allEntries = true)
            ]
    )
    fun deletePropertyTypeById(id: Long) {
        if (!propertyTypeRepository.existsById(id))
            throw AgencyException("Property type with the passed id:${id} doesn't exist!")
        propertyTypeRepository.deleteById(id)
        log.info("Evicting from caches: $PROPERTY_TYPES_BY_ID_CACHE for key: $id and from $PROPERTY_TYPES_ALL_CACHE all entries")
    }

    @Cacheable(value = [PROPERTY_TYPES_ALL_CACHE])
    fun findAll(): List<PropertyTypeDTO> {
        val list = propertyTypeMapper.mapListToDto(propertyTypeRepository.findAll())
        log.info("Storing into cache: $PROPERTY_TYPES_ALL_CACHE")
        return list
    }

    @CacheEvict(value = [PROPERTY_TYPES_ALL_CACHE, PROPERTY_TYPES_BY_ID_CACHE], allEntries = true)
    fun saveAll(list: List<PropertyTypeDTO>): List<PropertyTypeDTO> {
        list.forEach { if (it.propertyTypeId != null && propertyTypeRepository.existsById(it.propertyTypeId)) throw AgencyException("Property type with the passed id:${it.propertyTypeId} already exist!") }
        val listReturn = propertyTypeMapper.mapListToDto(propertyTypeRepository.saveAll(propertyTypeMapper.mapListToEntity(list)))
        log.info("Evicting from caches: $PROPERTY_TYPES_ALL_CACHE and $PROPERTY_TYPES_BY_ID_CACHE for all entries")
        return listReturn
    }

    @Cacheable(value = [PROPERTY_TYPES_BY_ID_CACHE], key = "{#id}")
    fun findById(id: Long): Optional<PropertyTypeDTO> {
        val type = propertyTypeRepository.findById(id)
        log.info("Storing into cache: $PROPERTY_TYPES_BY_ID_CACHE for key: $id")
        return when (type.isPresent) {
            true -> Optional.of(propertyTypeMapper.mapToDto(type.get()))
            else -> Optional.empty()
        }
    }

    companion object {
        const val PROPERTY_TYPES_ALL_CACHE = "propertyTypesAllCache"
        const val PROPERTY_TYPES_BY_ID_CACHE = "propertyTypesByIdCache"
    }
}