package com.agency.properties.mapper

import com.agency.properties.dto.LocationDTO
import com.agency.properties.entity.Location
import org.springframework.stereotype.Service

@Service
class LocationMapper {

    fun mapToDto(location: Location): LocationDTO {
        return LocationDTO(
                locationId = location.locationId,
                locationName = location.locationName,
                locationDescription = location.locationDescription
        )
    }

    fun mapToEntity(locationDTO: LocationDTO): Location {
        return Location(
                locationId = locationDTO.locationId,
                locationName = locationDTO.locationName,
                locationDescription = locationDTO.locationDescription
        )
    }

    fun mapListToDto(locations: Iterable<Location>): List<LocationDTO> {
        return locations.map { mapToDto(it) }
    }

    fun mapListToEntity(locations: Iterable<LocationDTO>): List<Location> {
        return locations.map { mapToEntity(it) }
    }
}