package com.agency.properties.rest

import com.agency.properties.dto.CompanyDetailsDTO
import com.agency.properties.entity.Agent
import com.agency.properties.entity.ProfilePicture
import com.agency.properties.repository.AgentRepository
import com.agency.properties.service.AgentService
import com.agency.properties.service.CompanyDetailsService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v0/companyDetails")
@CrossOrigin(originPatterns = ["*"])
class CompanyDetailsRestController @Autowired constructor(
        private val companyDetailsService: CompanyDetailsService,
        private val agentService: AgentService
) {

    /*@GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable("id") id: Long): ResponseEntity<CompanyDetailsDTO> {
        val details = companyDetailsService.findCompanyDetailsById(id)
        return when (details.isPresent) {
            true -> ResponseEntity(details.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }*/

    @Operation(summary = "Returns a list of company details which has only one element actually.")
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): ResponseEntity<List<CompanyDetailsDTO>> {
        val allDetails = companyDetailsService.findAllCompanyDetails()
        return when (allDetails.isNotEmpty()) {
            true -> ResponseEntity(allDetails, HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    /*@PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun save(@RequestBody detailsDTO: CompanyDetailsDTO): ResponseEntity<CompanyDetailsDTO> {
        val details = companyDetailsService.saveCompanyDetails(detailsDTO)
        return ResponseEntity(details, HttpStatus.OK)
    }*/

    @Operation(summary = "Updates the existing company details.")
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody detailsDTO: CompanyDetailsDTO): ResponseEntity<CompanyDetailsDTO> {
        val details = companyDetailsService.updateCompanyDetails(detailsDTO)
        return ResponseEntity(details, HttpStatus.OK)
    }

    /*@DeleteMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Any> {
        companyDetailsService.deleteCompanyDetailsById(id)
        return ResponseEntity(HttpStatus.OK)
    }*/
}