package com.agency.properties.rest

import com.agency.properties.dto.AgentDTO
import com.agency.properties.service.AgentService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v0/agents")
class AgentRestController @Autowired constructor(
        private val agentService: AgentService
) {

    @Operation(summary = "Gets an agent by his username.")
    @GetMapping("/findByUsername/{username}/{loadLazyParams}")
    fun findUserByUsername(@PathVariable("username") username: String, @PathVariable("loadLazyParams") loadLazyParams: Boolean): ResponseEntity<AgentDTO> {
        val agent = agentService.findByUsername(username, loadLazyParams)
        return when (agent.isPresent) {
            true -> ResponseEntity(agent.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @Operation(summary = "Gets an agent by id.")
    @GetMapping("/findById/{id}/{loadLazyParams}")
    fun findById(@PathVariable("id") id: Long, @PathVariable("loadLazyParams") loadLazyParams: Boolean): ResponseEntity<AgentDTO> {
        val agent = agentService.findById(id, loadLazyParams)
        return when (agent.isPresent) {
            true -> ResponseEntity(agent.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @Operation(summary = "Checks if the user by provided username exists.")
    @GetMapping("/existsByUsername/{username}")
    fun existsByUsername(@PathVariable("username") username: String): ResponseEntity<Boolean> {
        return ResponseEntity(agentService.existsByUsername(username), HttpStatus.OK)
    }

    @Operation(summary = "Saves a new user.")
    @PostMapping
    fun saveAgent(@RequestBody @Valid agentDTO: AgentDTO): ResponseEntity<AgentDTO> {
        return ResponseEntity(agentService.saveAgent(agentDTO), HttpStatus.OK)
    }

    @Operation(summary = "Updates an existing user.")
    @PutMapping
    fun updateAgent(@RequestBody @Valid agentDTO: AgentDTO): ResponseEntity<AgentDTO> {
        return ResponseEntity(agentService.updateAgent(agentDTO), HttpStatus.OK)
    }

    @Operation(summary = "Deletes an agent for the provided id.")
    @DeleteMapping("/{id}")
    fun deleteAgent(@PathVariable("id") id: Long): ResponseEntity<Any> {
        agentService.deleteAgentById(id)
        return ResponseEntity(HttpStatus.OK)
    }
}