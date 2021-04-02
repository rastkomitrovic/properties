package com.agency.properties.service

import com.agency.properties.dto.PropertyAttributeDTO
import com.agency.properties.mapper.PropertyAttributeMapper
import com.agency.properties.repository.PropertyAttributeRepository
import com.agency.properties.util.AgencyException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
@CacheConfig(cacheNames = [PropertyAttributeService.PROPERTY_ATTRIBUTES_ALL_CACHE, PropertyAttributeService.PROPERTY_ATTRIBUTES_BY_ID_CACHE])
class PropertyAttributeService @Autowired constructor(
        private val propertyAttributeRepository: PropertyAttributeRepository,
        private val propertyAttributeMapper: PropertyAttributeMapper
) {
    private val log = LoggerFactory.getLogger(PropertyTypeService::class.java)

    @CacheEvict(value = [PROPERTY_ATTRIBUTES_ALL_CACHE, PROPERTY_ATTRIBUTES_BY_ID_CACHE], allEntries = true)
    fun savePropertyAttribute(propertyAttributeDTO: PropertyAttributeDTO): PropertyAttributeDTO {
        if (propertyAttributeDTO.propertyAttributeId != null && propertyAttributeRepository.existsById(propertyAttributeDTO.propertyAttributeId))
            throw AgencyException("Property attribute with the passed id:${propertyAttributeDTO.propertyAttributeId} already exists!")
        val attribute = propertyAttributeMapper.mapToDto(propertyAttributeRepository.save(propertyAttributeMapper.mapToEntity(propertyAttributeDTO)))
        log.info("Evicting from caches: $PROPERTY_ATTRIBUTES_ALL_CACHE and $PROPERTY_ATTRIBUTES_BY_ID_CACHE all entries")
        return attribute
    }

    @Caching(
            evict = [
                CacheEvict(value = [PROPERTY_ATTRIBUTES_BY_ID_CACHE], key = "{#propertyAttributeDTO.propertyAttributeId}"),
                CacheEvict(value = [PROPERTY_ATTRIBUTES_ALL_CACHE], allEntries = true)
            ]
    )
    fun updatePropertyAttribute(propertyAttributeDTO: PropertyAttributeDTO): PropertyAttributeDTO {
        if (propertyAttributeDTO.propertyAttributeId != null && !propertyAttributeRepository.existsById(propertyAttributeDTO.propertyAttributeId))
            throw AgencyException("Property attribute with the passed id:${propertyAttributeDTO.propertyAttributeId} doesn't exist!")
        val attribute = propertyAttributeMapper.mapToDto(propertyAttributeRepository.save(propertyAttributeMapper.mapToEntity(propertyAttributeDTO)))
        log.info("Evicting from caches: $PROPERTY_ATTRIBUTES_BY_ID_CACHE for key: {${propertyAttributeDTO.propertyAttributeId}} and from $PROPERTY_ATTRIBUTES_ALL_CACHE all entries")
        return attribute
    }

    @Caching(
            evict = [
                CacheEvict(value = [PROPERTY_ATTRIBUTES_BY_ID_CACHE], key = "{#id}"),
                CacheEvict(value = [PROPERTY_ATTRIBUTES_ALL_CACHE], allEntries = true)
            ]
    )
    fun deletePropertyAttributeById(id: Long) {
        if (!propertyAttributeRepository.existsById(id))
            throw AgencyException("Property attribute with the passed id:${id} doesn't exist!")
        propertyAttributeRepository.deleteById(id)
        log.info("Evicting from caches: $PROPERTY_ATTRIBUTES_BY_ID_CACHE for key: {$id} and from $PROPERTY_ATTRIBUTES_ALL_CACHE all entries")
    }

    @Cacheable(value = [PROPERTY_ATTRIBUTES_ALL_CACHE])
    fun findAll(): List<PropertyAttributeDTO> {
        val list = propertyAttributeMapper.mapListToDto(propertyAttributeRepository.findAll())
        log.info("Storing into cache: $PROPERTY_ATTRIBUTES_ALL_CACHE")
        return list
    }

    @CacheEvict(value = [PROPERTY_ATTRIBUTES_ALL_CACHE, PROPERTY_ATTRIBUTES_BY_ID_CACHE], allEntries = true)
    fun saveAll(list: List<PropertyAttributeDTO>): List<PropertyAttributeDTO> {
        list.forEach { if (it.propertyAttributeId != null && propertyAttributeRepository.existsById(it.propertyAttributeId)) throw AgencyException("Property attribute with the passed id:${it.propertyAttributeId} already exists!") }
        val list = propertyAttributeMapper.mapListToDto(propertyAttributeRepository.saveAll(propertyAttributeMapper.mapListToEntity(list)))
        log.info("Evicting from cache: $PROPERTY_ATTRIBUTES_ALL_CACHE and $PROPERTY_ATTRIBUTES_BY_ID_CACHE all entries")
        return list
    }

    @Cacheable(value = [PROPERTY_ATTRIBUTES_BY_ID_CACHE], key = "{#id}")
    fun findById(id: Long): Optional<PropertyAttributeDTO> {
        val attribute = propertyAttributeRepository.findById(id)
        log.info("Storing into cache: $PROPERTY_ATTRIBUTES_BY_ID_CACHE for key: {$id}")
        return when (attribute.isPresent) {
            true -> Optional.of(propertyAttributeMapper.mapToDto(attribute.get()))
            else -> Optional.empty()
        }
    }

    companion object {
        const val PROPERTY_ATTRIBUTES_BY_ID_CACHE = "propertyAttributesByIdCache"
        const val PROPERTY_ATTRIBUTES_ALL_CACHE = "propertyAttributesAllCache"
    }
}