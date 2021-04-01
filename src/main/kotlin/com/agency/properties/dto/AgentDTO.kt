package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import java.util.*
import javax.validation.constraints.*

data class AgentDTO(
        @JsonProperty("agentId")
        @field:NotNull(message = "Agent's Id must not be null!")
        val agentId: Long?,

        @JsonProperty("agentFirstName")
        @field:NotNull(message = "Agent's first name must not be null!")
        @field:NotBlank(message = "Agent's first name can't be empty!")
        @field:Length(min = 2, max = 50, message = "Agent's first name must have between 2 and 50 characters!")
        val agentFirstName: String?,

        @JsonProperty("agentLastName")
        @field:NotNull(message = "Agent's last name must not be null!")
        @field:NotBlank(message = "Agent's last name can't be empty!")
        @field:Length(min = 2, max = 50, message = "Agent's last name must have between 2 and 50 characters!")
        val agentLastName: String?,

        @JsonProperty("agentUsername")
        @field:NotNull(message = "Agent's username must not be null!")
        @field:NotBlank(message = "Agent's username can't be empty!")
        @field:Length(min = 4, max = 50, message = "Agent's username must have between 4 and 50 characters!")
        val agentUsername: String?,

        @JsonProperty("agentPassword")
        @field:NotNull(message = "Agent's password must not be null!")
        @field:NotBlank(message = "Agent's password can't be empty!")
        @field:Length(min = 8, max = 50, message = "Agent's password name must have between 8 and 50 characters!")
        val agentPassword: String?,

        @JsonProperty("agentDateOfBirth")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        @field:NotNull(message = "Agent's date of birth must not be null!")
        @field:Past(message = "Agent's date of birth must be in the past!")
        val agentDateOfBirth: Date?,

        @JsonProperty("agentPhoneNumber")
        @field:NotNull(message = "Agent's phone number must not be null!")
        @field:NotBlank(message = "Agent's phone number can't be empty!")
        @field:Length(min = 5, max = 50, message = "Agent's phone number must have between 5 and 50 characters!")
        val agentPhoneNumber: String?,


        @JsonProperty("agentEmail")
        @field:NotNull(message = "Agent's email must not be null!")
        @field:NotBlank(message = "Agent's can't can't be empty!")
        @field:Email(message = "Agent's email must be in correct format (mailAddres@domain.something)!")
        val agentEmail: String?,

        @JsonProperty("agentAddress")
        @field:NotNull(message = "Agent's address must not be null!")
        @field:NotBlank(message = "Agent's address can't be empty!")
        @field:Length(min = 5, max = 85, message = "Agent's address must have between 5 and 85 characters!")
        val agentAddress: String?,

        @JsonProperty("agentIdNumber")
        @field:NotNull(message = "Agent's id number must not be null!")
        @field:NotBlank(message = "Agent's id number can't be empty!")
        @field:Length(min = 4, max = 50, message = "Agent's id number must have between 2 and 50 characters!")
        val agentIdNumber: String?,

        @JsonProperty("agentDescription")
        @field:NotNull(message = "Agent's description must not be null!")
        @field:NotBlank(message = "Agent's description can't be empty!")
        @field:Length(min = 10, max = 1000, message = "Agent's description must have between 10 and 1000 characters!")
        val agentDescription: String?,

        @JsonProperty("agentRole")
        val agentRole: RoleDTO?,

        @JsonProperty("location")
        val location: LocationDTO?,

        @JsonProperty("profilePicture")
        val profilePicture: ProfilePictureDTO?
)