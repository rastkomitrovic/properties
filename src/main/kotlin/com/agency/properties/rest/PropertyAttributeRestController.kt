package com.agency.properties.rest

import com.agency.properties.dto.PropertyAttributeDTO
import com.agency.properties.service.PropertyAttributeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/api/v0/propertyAttributes")
@CrossOrigin(originPatterns = ["*"])
class PropertyAttributeRestController @Autowired constructor(
        private val propertyAttributeService: PropertyAttributeService
) {

    @Operation(summary = "Saves a new property attribute.")
    @PostMapping
    fun savePropertyAttribute(@RequestBody @Valid propertyAttributeDTO: PropertyAttributeDTO): ResponseEntity<PropertyAttributeDTO> {
        return ResponseEntity(propertyAttributeService.savePropertyAttribute(propertyAttributeDTO), HttpStatus.OK)
    }

    @Operation(summary = "Updates an existing property attribute.")
    @PutMapping
    fun updatePropertyAttribute(@RequestBody @Valid propertyAttributeDTO: PropertyAttributeDTO): ResponseEntity<PropertyAttributeDTO> {
        return ResponseEntity(propertyAttributeService.updatePropertyAttribute(propertyAttributeDTO), HttpStatus.OK)
    }

    @Operation(summary = "Deletes a property attribute for the provided id.")
    @DeleteMapping("/{id}")
    fun deletePropertyAttribute(@PathVariable("id") id: Long): ResponseEntity<Any> {
        propertyAttributeService.deletePropertyAttributeById(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @Operation(summary = "Gets all property attributes.")
    @GetMapping
    fun getAll(): ResponseEntity<List<PropertyAttributeDTO>> {
        return ResponseEntity(propertyAttributeService.findAll(), HttpStatus.OK)
    }

    @Operation(summary = "Saves multiple property attributes.")
    @PostMapping("/saveMultiple")
    fun saveAll(@RequestBody @Valid list: List<PropertyAttributeDTO>): ResponseEntity<List<PropertyAttributeDTO>> {
        return ResponseEntity(propertyAttributeService.saveAll(list), HttpStatus.OK)
    }

    @Operation(summary = "Gets a property attribute for the provided id")
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<PropertyAttributeDTO> {
        val attribute = propertyAttributeService.findById(id)
        return when (attribute.isPresent) {
            true -> ResponseEntity(attribute.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}