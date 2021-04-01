package com.agency.properties.rest

import com.agency.properties.dto.OwnerDTO
import com.agency.properties.service.OwnerService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v0/owners")
class OwnerRestController @Autowired constructor(
        private val ownerService: OwnerService
) {

    @Operation(summary = "Saves a new owner.")
    @PostMapping
    fun saveOwner(@RequestBody @Valid ownerDTO: OwnerDTO): ResponseEntity<OwnerDTO> {
        return ResponseEntity(ownerService.saveOwner(ownerDTO), HttpStatus.OK)
    }

    @Operation(summary = "Updates an existing owner.")
    @PutMapping
    fun updateOwner(@RequestBody @Valid ownerDTO: OwnerDTO): ResponseEntity<OwnerDTO> {
        return ResponseEntity(ownerService.updateOwner(ownerDTO), HttpStatus.OK)
    }

    @Operation(summary = "Deletes an owner for the provided id")
    @DeleteMapping("/{id}")
    fun deleteOwner(@PathVariable("id") id: Long): ResponseEntity<Any> {
        ownerService.deleteOwnerById(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @Operation(summary = "Returns a page object for the input page, size and sort.")
    @GetMapping("/{page}/{size}/{sort}/{loadLazyParams}")
    fun findNoSearchPaging(@PathVariable("page") page: Int, @PathVariable("size") size: Int, @PathVariable("sort") sort: String, @PathVariable("loadLazyParams") loadLazyParams: Boolean): ResponseEntity<Page<OwnerDTO>> {
        val page = ownerService.findNoSearchPaging(page, size, sort, loadLazyParams)
        return when (page.isEmpty) {
            true -> ResponseEntity(HttpStatus.NO_CONTENT)
            else -> ResponseEntity(page, HttpStatus.OK)
        }
    }

    @Operation(summary = "Returns a page object for the input page, size, sort and search param.")
    @GetMapping("/{page}/{size}/{sort}/{param}/{loadLazyParams}")
    fun findSearchPaging(@PathVariable("page") page: Int, @PathVariable("size") size: Int, @PathVariable("sort") sort: String, @PathVariable("param") param: String, @PathVariable("loadLazyParams") loadLazyParams: Boolean): ResponseEntity<Page<OwnerDTO>> {
        val page = ownerService.findSearchPaging(page, size, sort, param, loadLazyParams)
        return when (page.isEmpty) {
            true -> ResponseEntity(HttpStatus.NO_CONTENT)
            else -> ResponseEntity(page, HttpStatus.OK)
        }
    }

    @Operation(summary = "Returns an owner for the provided id.")
    @GetMapping("/{id}(/{loadLazyParams}")
    fun findById(@PathVariable("id") id: Long, @PathVariable("loadLazyParams") loadLazyParams: Boolean): ResponseEntity<OwnerDTO> {
        val owner = ownerService.findById(id, loadLazyParams)
        return when (owner.isPresent) {
            true -> ResponseEntity(owner.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }
}