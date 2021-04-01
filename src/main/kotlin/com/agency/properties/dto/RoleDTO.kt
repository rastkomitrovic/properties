package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class RoleDTO(
        @JsonProperty("roleId")
        @field:NotNull(message = "Role's id must not be null!")
        val roleId: Long?,

        @JsonProperty("roleName")
        @field:NotNull(message = "Role's name must not be null!")
        @field:NotBlank(message = "Role's name can't be empty!")
        @field:Length(min = 2, max = 50, message = "Role's name must have between 2 and 50 characters!")
        val roleName: String?,

        @JsonProperty("roleDescription")
        val roleDescription: String?
)
