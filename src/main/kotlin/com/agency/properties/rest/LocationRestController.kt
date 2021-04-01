package com.agency.properties.rest

import com.agency.properties.dto.LocationDTO
import com.agency.properties.service.LocationService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v0/locations")
class LocationRestController @Autowired constructor(
        private val locationService: LocationService
) {

    @Operation(summary = "Saves a new location.")
    @PostMapping
    fun saveLocation(@RequestBody @Valid locationDTO: LocationDTO): ResponseEntity<LocationDTO> {
        return ResponseEntity(locationService.saveLocation(locationDTO), HttpStatus.OK)
    }

    @Operation(summary = "Updates an existing location.")
    @PutMapping
    fun updateLocation(@RequestBody @Valid locationDTO: LocationDTO): ResponseEntity<LocationDTO> {
        return ResponseEntity(locationService.updateLocation(locationDTO), HttpStatus.OK)
    }

    @Operation(summary = "Deletes a location for the provided id.")
    @DeleteMapping("/{id}")
    fun deleteLocation(@PathVariable("id") id: Long): ResponseEntity<Any> {
        locationService.deleteLocationById(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @Operation(summary = "Gets all locations.")
    @GetMapping
    fun getAllLocations(): ResponseEntity<List<LocationDTO>> {
        return ResponseEntity(locationService.findAll(), HttpStatus.OK)
    }

    @Operation(summary = "Saves multiple locations.")
    @PostMapping("/saveMultiple")
    fun saveAll(@RequestBody list: List<LocationDTO>): ResponseEntity<List<LocationDTO>> {
        return ResponseEntity(locationService.saveAll(list), HttpStatus.OK)
    }
}