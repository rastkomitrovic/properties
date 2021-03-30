package com.agency.properties.rest

import com.agency.properties.dto.AgentDTO
import com.agency.properties.service.AgentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/agents")
class AgentRestController @Autowired constructor(
        private val agentService: AgentService
) {

    @GetMapping("/{username}/{includeProfilePicture}")
    fun findUserByUsername(@PathVariable("username") username: String, @PathVariable("includeProfilePicture") includeProfilePicture: Boolean): ResponseEntity<AgentDTO> {
        val agent = agentService.findByUsername(username, includeProfilePicture)
        return when (agent.isPresent) {
            true -> ResponseEntity(agent.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping("/{username}")
    fun existsByUsername(@PathVariable("username") username: String): ResponseEntity<Boolean> {
        return ResponseEntity(agentService.existsByUsername(username), HttpStatus.OK)
    }

    @PostMapping
    fun saveAgent(@RequestBody agentDTO: AgentDTO):ResponseEntity<AgentDTO>{
        return ResponseEntity(agentService.saveAgent(agentDTO),HttpStatus.OK)
    }

    @PutMapping
    fun updateAgent(@RequestBody agentDTO: AgentDTO):ResponseEntity<AgentDTO>{
        return ResponseEntity(agentService.updateAgent(agentDTO),HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteAgent(@PathVariable("id") id:Long):ResponseEntity<Any>{
        agentService.deleteAgentById(id)
        return ResponseEntity(HttpStatus.OK)
    }
}