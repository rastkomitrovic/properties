package com.agency.properties.service

import com.agency.properties.dto.RoleDTO
import com.agency.properties.mapper.RoleMapper
import com.agency.properties.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class RoleService @Autowired constructor(
        private val roleRepository: RoleRepository,
        private val roleMapper: RoleMapper
) {

    fun findAll(): List<RoleDTO> {
        return roleMapper.mapListToDto(roleRepository.findAll())
    }
}