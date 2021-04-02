package com.agency.properties.rest

import com.agency.properties.dto.PropertyTypeDTO
import com.agency.properties.service.PropertyTypeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/api/v0/propertyTypes")
@CrossOrigin(originPatterns = ["*"])
class PropertyTypeRestController @Autowired constructor(
        private val propertyTypeService: PropertyTypeService
) {
    @Operation(summary = "Saves a new property type.")
    @PostMapping
    fun savePropertyType(@RequestBody @Valid propertyTypeDTO: PropertyTypeDTO): ResponseEntity<PropertyTypeDTO> {
        return ResponseEntity(propertyTypeService.savePropertyType(propertyTypeDTO), HttpStatus.OK)
    }

    @Operation(summary = "Updates an existing property type.")
    @PutMapping
    fun updatePropertyType(@RequestBody @Valid propertyTypeDTO: PropertyTypeDTO): ResponseEntity<PropertyTypeDTO> {
        return ResponseEntity(propertyTypeService.updatePropertyType(propertyTypeDTO), HttpStatus.OK)
    }

    @Operation(summary = "Deletes a property type for the provided id.")
    @DeleteMapping("/{id}")
    fun deletePropertyType(@PathVariable("id") id: Long): ResponseEntity<Any> {
        propertyTypeService.deletePropertyTypeById(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @Operation(summary = "Gets all property types.")
    @GetMapping
    fun getAll(): ResponseEntity<List<PropertyTypeDTO>> {
        return ResponseEntity(propertyTypeService.findAll(), HttpStatus.OK)
    }

    @Operation(summary = "Saves multiple property types.")
    @PostMapping("/saveMultiple")
    fun saveAll(@RequestBody @Valid list: List<PropertyTypeDTO>): ResponseEntity<List<PropertyTypeDTO>> {
        return ResponseEntity(propertyTypeService.saveAll(list), HttpStatus.OK)
    }

    @Operation(summary = "Gets a property type for the provided id.")
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<PropertyTypeDTO> {
        val type = propertyTypeService.findById(id)
        return when (type.isPresent) {
            true -> ResponseEntity(type.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}