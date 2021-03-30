package com.agency.properties.service

import com.agency.properties.dto.AgentDTO
import com.agency.properties.entity.Agent
import com.agency.properties.mapper.AgentMapper
import com.agency.properties.repository.AgentRepository
import com.agency.properties.util.AgencyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class AgentService @Autowired constructor(
        private val agentRepository: AgentRepository,
        private val agentMapper: AgentMapper
) {

    fun findByUsername(username: String, includeProfilePicture: Boolean): Optional<AgentDTO> {
        return returnAgentDTOFromOptional(
                when (includeProfilePicture) {
                    true -> agentRepository.findByUsernameWithPicture(username)
                    else -> agentRepository.findByUsernameWithoutPicture(username)
                }
        )
    }

    fun saveAgent(agentDTO:AgentDTO):AgentDTO{
        if(agentDTO.agentId!=null && agentRepository.existsById(agentDTO.agentId!!))
            throw AgencyException("Agent with the passed id:${agentDTO.agentId} already exists!")
        return agentMapper.mapToDto(agentRepository.save(agentMapper.mapToEntity(agentDTO)))
    }

    fun updateAgent(agentDTO:AgentDTO):AgentDTO{
        if(agentDTO.agentId!=null && !agentRepository.existsById(agentDTO.agentId!!))
            throw AgencyException("Agent with the passed id:${agentDTO.agentId} doesn't exist!")
        return agentMapper.mapToDto(agentRepository.save(agentMapper.mapToEntity(agentDTO)))
    }

    fun deleteAgentById(id:Long){
        if(!agentRepository.existsById(id))
            throw AgencyException("Agent with the passed id:${id} doesn't exist")
        agentRepository.deleteById(id)
    }
    fun existsByUsername(username: String): Boolean {
        val returnedUsername = agentRepository.findUsername(username)
        return when (returnedUsername.isPresent) {
            true -> returnedUsername.get() == username
            else -> false
        }
    }

    private fun returnAgentDTOFromOptional(agent: Optional<Agent>): Optional<AgentDTO> {
        return when (agent.isPresent) {
            true -> Optional.of(agentMapper.mapToDto(agent.get()))
            else -> Optional.empty()
        }
    }
}