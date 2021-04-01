package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class OwnerDTO(
        @JsonProperty("ownerId")
        @field:NotNull(message = "Owner's Id must not be null!")
        val ownerId: Long? = null,

        @JsonProperty("ownerFirstName")
        @field:NotNull(message = "Owner's first name must not be null!")
        @field:NotBlank(message = "Owner's first name can't be empty!")
        @field:Length(min = 2, max = 50, message = "Owner's first name must have between 2 and 50 characters!")
        val ownerFirstName: String? = null,

        @JsonProperty("ownerLastName")
        @field:NotNull(message = "Owner's last name must not be null!")
        @field:NotBlank(message = "Owner's last name can't be empty!")
        @field:Length(min = 2, max = 50, message = "Owner's last name must have between 2 and 50 characters!")
        val ownerLastName: String? = null,

        @JsonProperty("ownerEmail")
        @field:NotNull(message = "Owner's email must not be null!")
        @field:NotBlank(message = "Owner's email can't be empty!")
        @field:Email(message = "Owner's email must be in correct format(mailAddres@domain.something)!")
        val ownerEmail: String? = null,

        @JsonProperty("ownerPhoneNumber")
        @field:NotNull(message = "Owner's phone number must not be null!")
        @field:NotBlank(message = "Owner's phone number can't be empty!")
        @field:Length(min = 5, max = 50, message = "Owner's phone number must have between 5 and 50 characters!")
        val ownerPhoneNumber: String? = null,

        @JsonProperty("ownerAddress")
        @field:NotNull(message = "Owner's address must not be null!")
        @field:NotBlank(message = "Owner's address can't be empty!")
        @field:Length(min = 5, max = 85, message = "Owner's address must have between 5 and 85 characters!")
        val ownerAddress: String? = null,

        @JsonProperty("ownerDescription")
        val ownerDescription: String? = null,

        @JsonProperty("addedByAgent")
        val addedByAgent: AgentDTO? = null,

        @JsonProperty("location")
        val location: LocationDTO? = null
)
