package com.agency.properties.service

import com.agency.properties.dto.RentTypeDTO
import com.agency.properties.mapper.RentTypeMapper
import com.agency.properties.repository.RentTypeRepository
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
@CacheConfig(cacheNames = [RentTypeService.RENT_TYPES_ALL_CACHE, RentTypeService.RENT_TYPES_BY_ID_CACHE])
class RentTypeService @Autowired constructor(
        private val rentTypeRepository: RentTypeRepository,
        private val rentTypeMapper: RentTypeMapper
) {
    private val log = LoggerFactory.getLogger(RentTypeService::class.java)

    @CacheEvict(value = [RENT_TYPES_ALL_CACHE, RENT_TYPES_BY_ID_CACHE], allEntries = true)
    fun saveRentType(rentTypeDTO: RentTypeDTO): RentTypeDTO {
        if (rentTypeDTO.rentTypeId != null && rentTypeRepository.existsById(rentTypeDTO.rentTypeId))
            throw AgencyException("Rent type with the passed id:${rentTypeDTO.rentTypeId} already exists!")
        val type = rentTypeMapper.mapToDto(rentTypeRepository.save(rentTypeMapper.mapToEntity(rentTypeDTO)))
        log.info("Evicting from caches: $RENT_TYPES_ALL_CACHE and $RENT_TYPES_BY_ID_CACHE all entries")
        return type
    }

    @Caching(
            evict = [
                CacheEvict(value = [RENT_TYPES_ALL_CACHE], allEntries = true),
                CacheEvict(value = [RENT_TYPES_BY_ID_CACHE], key = "{#rentTypeDTO.rentTypeId}")
            ]
    )
    fun updateRentType(rentTypeDTO: RentTypeDTO): RentTypeDTO {
        if (rentTypeDTO.rentTypeId != null && !rentTypeRepository.existsById(rentTypeDTO.rentTypeId))
            throw AgencyException("Rent type with the passed id:${rentTypeDTO.rentTypeId} doesn't exists!")
        val type = rentTypeMapper.mapToDto(rentTypeRepository.save(rentTypeMapper.mapToEntity(rentTypeDTO)))
        log.info("Evicting from caches: $RENT_TYPES_ALL_CACHE all entries and from $RENT_TYPES_BY_ID_CACHE for key: {${rentTypeDTO.rentTypeId}}")
        return type
    }

    @Caching(
            evict = [
                CacheEvict(value = [RENT_TYPES_ALL_CACHE], allEntries = true),
                CacheEvict(value = [RENT_TYPES_BY_ID_CACHE], key = "{#id}")
            ]
    )
    fun deleteRentTypeById(id: Long) {
        if (!rentTypeRepository.existsById(id))
            throw AgencyException("Rent type with the passed id:${id} doesn't exists!")
        rentTypeRepository.deleteById(id)
        log.info("Evicting from caches: $RENT_TYPES_ALL_CACHE all entries and from $RENT_TYPES_BY_ID_CACHE for key: {${id}}")
    }

    @Cacheable(value = [RENT_TYPES_ALL_CACHE])
    fun findAll(): List<RentTypeDTO> {
        val type = rentTypeMapper.mapListToDto(rentTypeRepository.findAll())
        log.info("Storing into cache: $RENT_TYPES_ALL_CACHE")
        return type
    }

    @CacheEvict(value = [RENT_TYPES_ALL_CACHE, RENT_TYPES_BY_ID_CACHE], allEntries = true)
    fun saveAll(list: List<RentTypeDTO>): List<RentTypeDTO> {
        list.forEach { if (it.rentTypeId != null && rentTypeRepository.existsById(it.rentTypeId)) throw AgencyException("Rent type with the passed id:${it.rentTypeId} already exists!") }
        val listReturn = rentTypeMapper.mapListToDto(rentTypeRepository.findAll())
        log.info("Evicting from caches: $RENT_TYPES_ALL_CACHE and $RENT_TYPES_BY_ID_CACHE all entries")
        return listReturn
    }

    @Cacheable(value = [RENT_TYPES_BY_ID_CACHE], key = "{#id}")
    fun findById(id: Long): Optional<RentTypeDTO> {
        val type = rentTypeRepository.findById(id)
        log.info("Storing into cache: $RENT_TYPES_ALL_CACHE for key: $id")
        return when (type.isPresent) {
            true -> Optional.of(rentTypeMapper.mapToDto(type.get()))
            else -> Optional.empty()
        }
    }

    companion object {
        const val RENT_TYPES_ALL_CACHE = "rentTypesAllCache"
        const val RENT_TYPES_BY_ID_CACHE = "rentTypesByIdCache"
    }
}