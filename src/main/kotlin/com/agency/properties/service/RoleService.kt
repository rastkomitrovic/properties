package com.agency.properties.service

import com.agency.properties.dto.RoleDTO
import com.agency.properties.mapper.RoleMapper
import com.agency.properties.repository.RoleRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
@CacheConfig(cacheNames = [RoleService.ROLES_ALL_CACHE])
class RoleService @Autowired constructor(
        private val roleRepository: RoleRepository,
        private val roleMapper: RoleMapper
) {
    private val log = LoggerFactory.getLogger(RoleService::class.java)

    @Cacheable(value = [ROLES_ALL_CACHE])
    fun findAll(): List<RoleDTO> {
        val list = roleMapper.mapListToDto(roleRepository.findAll())
        log.info("Storing into cache: $ROLES_ALL_CACHE")
        return list
    }

    companion object {
        const val ROLES_ALL_CACHE = "rolesAllCache"
    }

}