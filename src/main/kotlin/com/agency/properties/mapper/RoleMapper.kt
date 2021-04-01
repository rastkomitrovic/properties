package com.agency.properties.mapper

import com.agency.properties.dto.RoleDTO
import com.agency.properties.entity.Role
import org.springframework.stereotype.Service

@Service
class RoleMapper {

    fun mapToDto(role: Role): RoleDTO {
        return RoleDTO(
                roleId = role.roleId,
                roleName = role.roleName,
                roleDescription = role.roleDescription
        )
    }

    fun mapToEntity(roleDTO: RoleDTO): Role {
        return Role(
                roleId = roleDTO.roleId,
                roleName = roleDTO.roleName,
                roleDescription = roleDTO.roleDescription
        )
    }

    fun mapListToDto(roles: Iterable<Role>): List<RoleDTO> {
        return roles.map { mapToDto(it) }
    }

    fun mapListToEntity(roles: Iterable<RoleDTO>): List<Role> {
        return roles.map { mapToEntity(it) }
    }
}