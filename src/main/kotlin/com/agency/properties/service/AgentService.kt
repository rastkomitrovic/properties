package com.agency.properties.service

import com.agency.properties.dto.AgentDTO
import com.agency.properties.entity.Agent
import com.agency.properties.mapper.AgentMapper
import com.agency.properties.repository.AgentRepository
import com.agency.properties.util.AgencyException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
@CacheConfig(cacheNames = [AgentService.AGENTS_BY_USERNAME_CACHE, AgentService.AGENTS_BY_ID_CACHE])
class AgentService @Autowired constructor(
        private val agentRepository: AgentRepository,
        private val agentMapper: AgentMapper
) {
    private val log = LoggerFactory.getLogger(AgentService::class.java)

    @Cacheable(AGENTS_BY_USERNAME_CACHE, key = "{#username,#loadLazyParams}")
    fun findByUsername(username: String, loadLazyParams: Boolean): Optional<AgentDTO> {
        val agent = returnAgentDTOFromOptional(
                when (loadLazyParams) {
                    true -> agentRepository.findByUsernameLoadLazyEntities(username)
                    else -> agentRepository.findByUsernameNotLoadLazyEntities(username)
                }
        )
        log.info("Storing in cache: $AGENTS_BY_USERNAME_CACHE for key: {$username,$loadLazyParams}")
        return agent
    }

    @Cacheable(AGENTS_BY_ID_CACHE, key = "{#id,#loadLazyParams}")
    fun findById(id: Long, loadLazyParams: Boolean): Optional<AgentDTO> {
        val agent = returnAgentDTOFromOptional(
                when (loadLazyParams) {
                    true -> agentRepository.findByIdLoadLazyEntities(id)
                    else -> agentRepository.findById(id)
                }
        )
        log.info("Storing in cache: $AGENTS_BY_ID_CACHE kor key: {$id,$loadLazyParams}")
        return agent
    }

    @CacheEvict(value = [AGENTS_BY_USERNAME_CACHE, AGENTS_BY_ID_CACHE, AGENTS_EXISTING_BY_USERNAME_CACHE], allEntries = true)
    fun saveAgent(agentDTO: AgentDTO): AgentDTO {
        if (agentDTO.agentId != null && agentRepository.existsById(agentDTO.agentId!!))
            throw AgencyException("Agent with the passed id:${agentDTO.agentId} already exists!")
        val agent = agentMapper.mapToDto(agentRepository.save(agentMapper.mapToEntity(agentDTO)))
        log.info("Evicting from caches: $AGENTS_BY_USERNAME_CACHE, $AGENTS_BY_ID_CACHE and $AGENTS_EXISTING_BY_USERNAME_CACHE all entries")
        return agent
    }

    @CacheEvict(value = [AGENTS_BY_USERNAME_CACHE, AGENTS_BY_ID_CACHE, AGENTS_EXISTING_BY_USERNAME_CACHE], allEntries = true)
    fun updateAgent(agentDTO: AgentDTO): AgentDTO {
        if (agentDTO.agentId != null && !agentRepository.existsById(agentDTO.agentId!!))
            throw AgencyException("Agent with the passed id:${agentDTO.agentId} doesn't exist!")
        val agent = agentMapper.mapToDto(agentRepository.save(agentMapper.mapToEntity(agentDTO)))
        log.info("Evicting all from caches: $AGENTS_BY_USERNAME_CACHE, $AGENTS_BY_ID_CACHE and $AGENTS_EXISTING_BY_USERNAME_CACHE")
        return agent
    }


    @CacheEvict(value = [AGENTS_BY_USERNAME_CACHE, AGENTS_BY_ID_CACHE, AGENTS_EXISTING_BY_USERNAME_CACHE], allEntries = true)
    fun deleteAgentById(id: Long) {
        if (!agentRepository.existsById(id))
            throw AgencyException("Agent with the passed id:${id} doesn't exist")
        agentRepository.deleteById(id)
        log.info("Evicting all from caches: $AGENTS_BY_USERNAME_CACHE, $AGENTS_BY_ID_CACHE and $AGENTS_EXISTING_BY_USERNAME_CACHE")
    }

    @Cacheable(value = [AGENTS_EXISTING_BY_USERNAME_CACHE], key = "{#username}")
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


    companion object {
        const val AGENTS_BY_ID_CACHE = "agentsByIdCache"
        const val AGENTS_BY_USERNAME_CACHE = "agentsByUsernameCache"
        const val AGENTS_EXISTING_BY_USERNAME_CACHE = "agentsExistingByUsernameCache"
    }
}