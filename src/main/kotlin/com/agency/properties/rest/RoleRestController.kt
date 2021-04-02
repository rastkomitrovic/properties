package com.agency.properties.rest

import com.agency.properties.dto.RoleDTO
import com.agency.properties.service.RoleService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v0/roles")
@CrossOrigin(originPatterns = ["*"])
class RoleRestController @Autowired constructor(
        private val roleService: RoleService
) {

    @Operation(summary = "Gets all roles.")
    @GetMapping
    fun getAll(): ResponseEntity<List<RoleDTO>> {
        return ResponseEntity(roleService.findAll(), HttpStatus.OK)
    }
}