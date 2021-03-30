package com.agency.properties.mapper

import com.agency.properties.dto.AgentDTO
import com.agency.properties.entity.Agent
import com.agency.properties.util.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AgentMapper @Autowired constructor(
        private val profilePictureMapper: ProfilePictureMapper,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun mapToDto(agent: Agent): AgentDTO {
        return AgentDTO(
                agentId = agent.agentId,
                agentFirstName = agent.agentFirstName,
                agentLastName = agent.agentLastName,
                agentUsername = agent.agentUsername,
                agentPassword = agent.agentPassword,
                agentDateOfBirth = agent.agentDateOfBirth,
                agentPhoneNumber = agent.agentPhoneNumber,
                agentEmail = agent.agentEmail,
                agentAddress = agent.agentAddress,
                agentIdNumber = agent.agentIdNumber,
                agentDescription = agent.agentDescription,
                profilePicture = if (Utils.isLazyEntityInitialized(agent.profilePicture)) agent.profilePicture?.let { profilePictureMapper.mapToDto(it) } else null
        )
    }

    fun mapToEntity(agentDTO: AgentDTO): Agent {
        return Agent(
                agentId = agentDTO.agentId,
                agentFirstName = agentDTO.agentFirstName,
                agentLastName = agentDTO.agentLastName,
                agentUsername = agentDTO.agentUsername,
                agentPassword = bCryptPasswordEncoder.encode(agentDTO.agentPassword),
                agentDateOfBirth = agentDTO.agentDateOfBirth,
                agentPhoneNumber = agentDTO.agentPhoneNumber,
                agentEmail = agentDTO.agentEmail,
                agentAddress = agentDTO.agentAddress,
                agentIdNumber = agentDTO.agentIdNumber,
                agentDescription = agentDTO.agentDescription,
                profilePicture = agentDTO.profilePicture?.let { profilePictureMapper.mapToEntity(it) }
        )
    }
}