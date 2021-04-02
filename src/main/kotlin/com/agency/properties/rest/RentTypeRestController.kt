package com.agency.properties.rest

import com.agency.properties.dto.RentTypeDTO
import com.agency.properties.service.RentTypeService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("/api/v0/rentTypes")
@CrossOrigin(originPatterns = ["*"])
class RentTypeRestController @Autowired constructor(
        private val rentTypeService: RentTypeService
) {

    @Operation(summary = "Save a new rent type.")
    @PostMapping
    fun saveRentType(@RequestBody @Valid rentTypeDTO: RentTypeDTO): ResponseEntity<RentTypeDTO> {
        return ResponseEntity(rentTypeService.saveRentType(rentTypeDTO), HttpStatus.OK)
    }

    @Operation(summary = "Updates an existing rent type.")
    @PutMapping
    fun updateRentType(@RequestBody @Valid rentTypeDTO: RentTypeDTO): ResponseEntity<RentTypeDTO> {
        return ResponseEntity(rentTypeService.updateRentType(rentTypeDTO), HttpStatus.OK)
    }

    @Operation(summary = "Deletes a rent type for the provided id")
    @DeleteMapping("/{id}")
    fun deleteRentType(@PathVariable("id") id: Long): ResponseEntity<Any> {
        rentTypeService.deleteRentTypeById(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @Operation(summary = "Gets all rent types.")
    @GetMapping
    fun getAll(): ResponseEntity<List<RentTypeDTO>> {
        return ResponseEntity(rentTypeService.findAll(), HttpStatus.OK)
    }

    @Operation(summary = "Saves multiple rent types")
    @PostMapping("/saveMultiple")
    fun saveAll(@RequestBody @Valid list: List<RentTypeDTO>): ResponseEntity<List<RentTypeDTO>> {
        return ResponseEntity(rentTypeService.saveAll(list), HttpStatus.OK)
    }

    @Operation(summary = "Gets a rent type for the provided id")
    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ResponseEntity<RentTypeDTO> {
        val type = rentTypeService.findById(id)
        return when (type.isPresent) {
            true -> ResponseEntity(type.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}