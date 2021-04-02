package com.agency.properties.service

import com.agency.properties.dto.LocationDTO
import com.agency.properties.mapper.LocationMapper
import com.agency.properties.repository.LocationRepository
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
@CacheConfig(cacheNames = [LocationService.LOCATIONS_ALL_CACHE, LocationService.LOCATIONS_BY_ID_CACHE])
class LocationService @Autowired constructor(
        private val locationRepository: LocationRepository,
        private val locationMapper: LocationMapper
) {
    private val log = LoggerFactory.getLogger(LocationService::class.java)

    @CacheEvict(value = [LOCATIONS_ALL_CACHE, LOCATIONS_BY_ID_CACHE], allEntries = true)
    fun saveLocation(locationDTO: LocationDTO): LocationDTO {
        if (locationDTO.locationId != null && locationRepository.existsById(locationDTO.locationId))
            throw AgencyException("Location with the passed id:${locationDTO.locationId} already exists!")
        val location = locationMapper.mapToDto(locationRepository.save(locationMapper.mapToEntity(locationDTO)))
        log.info("Evicting from caches: $LOCATIONS_ALL_CACHE all entries.")
        return location
    }

    @Caching(
            evict = [
                CacheEvict(value = [LOCATIONS_BY_ID_CACHE], key = "#locationDTO.locationId"),
                CacheEvict(value = [LOCATIONS_ALL_CACHE], allEntries = true)
            ]
    )
    fun updateLocation(locationDTO: LocationDTO): LocationDTO {
        if (locationDTO.locationId != null && !locationRepository.existsById(locationDTO.locationId))
            throw AgencyException("Location with the passed id:${locationDTO.locationId} doesn't exist!")
        val location = locationMapper.mapToDto(locationRepository.save(locationMapper.mapToEntity(locationDTO)))
        log.info("Evicting from caches: $LOCATIONS_BY_ID_CACHE for key: ${locationDTO.locationId} and $LOCATIONS_ALL_CACHE all entries")
        return location
    }

    @Caching(
            evict = [
                CacheEvict(value = [LOCATIONS_BY_ID_CACHE], key = "#id"),
                CacheEvict(value = [LOCATIONS_ALL_CACHE], allEntries = true)
            ]
    )
    fun deleteLocationById(id: Long) {
        if (!locationRepository.existsById(id))
            throw AgencyException("Location with the passed id:${id} doesn't exist!")
        log.info("Evicting from caches: $LOCATIONS_BY_ID_CACHE for key: $id and $LOCATIONS_ALL_CACHE all entries")
        locationRepository.deleteById(id)
    }

    @Cacheable(value = [LOCATIONS_ALL_CACHE])
    fun findAll(): List<LocationDTO> {
        val locations = locationMapper.mapListToDto(locationRepository.findAll())
        log.info("Storing into cache: $LOCATIONS_ALL_CACHE")
        return locations
    }

    @CacheEvict(value = [LOCATIONS_ALL_CACHE, LOCATIONS_BY_ID_CACHE], allEntries = true)
    fun saveAll(list: List<LocationDTO>): List<LocationDTO> {
        list.forEach { if (it.locationId != null && locationRepository.existsById(it.locationId)) throw AgencyException("Location with the passed id:${it.locationId} already exists!") }
        val locations = locationMapper.mapListToDto(locationRepository.saveAll(locationMapper.mapListToEntity(list)))
        log.info("Evicting from caches: $LOCATIONS_ALL_CACHE and $LOCATIONS_BY_ID_CACHE all entries")
        return locations
    }

    @Cacheable(value = [LOCATIONS_BY_ID_CACHE], key = "#id")
    fun findById(id: Long): Optional<LocationDTO> {
        val location = locationRepository.findById(id)
        log.info("Storing into cache: $LOCATIONS_BY_ID_CACHE for key: $id")
        return when (location.isPresent) {
            true -> Optional.of(locationMapper.mapToDto(location.get()))
            else -> Optional.empty()
        }
    }

    companion object {
        const val LOCATIONS_ALL_CACHE = "allLocationsCache"
        const val LOCATIONS_BY_ID_CACHE = "locationsByIdCache"
    }
}