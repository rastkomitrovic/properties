package com.agency.properties.service

import com.agency.properties.dto.LocationDTO
import com.agency.properties.mapper.LocationMapper
import com.agency.properties.repository.LocationRepository
import com.agency.properties.util.AgencyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationService @Autowired constructor(
        private val locationRepository: LocationRepository,
        private val locationMapper: LocationMapper
) {

    fun saveLocation(locationDTO: LocationDTO): LocationDTO {
        if (locationDTO.locationId != null && locationRepository.existsById(locationDTO.locationId))
            throw AgencyException("Location with the passed id:${locationDTO.locationId} already exists!")
        return locationMapper.mapToDto(locationRepository.save(locationMapper.mapToEntity(locationDTO)))
    }

    fun updateLocation(locationDTO: LocationDTO): LocationDTO {
        if (locationDTO.locationId != null && !locationRepository.existsById(locationDTO.locationId))
            throw AgencyException("Location with the passed id:${locationDTO.locationId} doesn't exist!")
        return locationMapper.mapToDto(locationRepository.save(locationMapper.mapToEntity(locationDTO)))
    }

    fun deleteLocationById(id: Long) {
        if (!locationRepository.existsById(id))
            throw AgencyException("Location with the passed id:${id} doesn't exist!")
        locationRepository.deleteById(id)
    }

    fun findAll(): List<LocationDTO> {
        return locationMapper.mapListToDto(locationRepository.findAll())
    }

    fun saveAll(list: List<LocationDTO>): List<LocationDTO> {
        list.forEach { if (it.locationId != null && locationRepository.existsById(it.locationId)) throw AgencyException("Location with the passed id:${it.locationId} already exists!") }
        return locationMapper.mapListToDto(locationRepository.saveAll(locationMapper.mapListToEntity(list)))
    }

}