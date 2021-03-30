package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.validation.constraints.*

data class AgentDTO(
        @JsonProperty("agentId")
        @field:NotNull(message = "Agents Id must not be null!")
        val agentId: Long?,

        @JsonProperty("agentFirstName")
        @field:NotNull(message = "Agents first name must not be null!")
        @field:Min(value = 2, message = "Agents first name must be at least 2 characters long!")
        @field:Max(value = 30, message = "Agents first name must be less than 31 characters long!")
        val agentFirstName: String?,

        @JsonProperty("agentLastName")
        @field:NotNull(message = "Agents last name must not be null!")
        @field:Min(value = 2, message = "Agents last name must be at least 2 characters long!")
        @field:Max(value = 30, message = "Agents last name must be less than 31 characters long!")
        val agentLastName: String?,

        @JsonProperty("agentUsername")
        @field:NotNull(message = "Agents username must not be null!")
        @field:Min(value = 4, message = "Agents username must be at least 4 characters long!")
        @field:Max(value = 30, message = "Agents username must be less than 31 characters long!")
        val agentUsername: String?,

        @JsonProperty("agentPassword")
        @field:NotNull(message = "Agents password must not be null!")
        @field:Min(value = 8, message = "Agents password must be at least 8 characters long!")
        @field:Max(value = 30, message = "Agents password must be less than 31 characters long!")
        val agentPassword: String?,

        @JsonProperty("agentDateOfBirth")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        @field:NotNull(message = "Agents date of birth must not be null!")
        @field:Past(message = "Agents date of birth must be in the past!")
        val agentDateOfBirth: Date?,

        @JsonProperty("agentPhoneNumber")
        @field:NotNull(message = "Agents phone number must not be null!")
        @field:Min(value = 5, message = "Phone number must be at least 5 characters long!")
        @field:Max(value = 40, message = "Phone number must be less than 41 characters long!")
        val agentPhoneNumber: String?,


        @JsonProperty("agentEmail")
        @field:NotNull(message = "Agents email must not be null!")
        @field:Email(message = "Agents email must be in correct format (mailAddres@domain)!")
        val agentEmail: String?,

        @JsonProperty("agentAddress")
        @field:NotNull(message = "Agents address must not be null!")
        @field:Min(value = 5, message = "Agents address must be at least 5 characters long!")
        @field:Max(value = 85, message = "Agents address must be less than 86 characters long!")
        val agentAddress: String?,

        @JsonProperty("agentIdNumber")
        @field:NotNull(message = "Agents id number must not be null!")
        @field:Min(value = 4, message = "Agents Id number must be at least 4 characters long!")
        @field:Max(value = 40, message = "Agents Id must be less than 41 characters long!")
        val agentIdNumber: String?,

        @JsonProperty("agentDescription")
        @field:NotNull(message = "Agents description must not be null!")
        @field:Min(value = 10, message = "Agents description must be at least 10 characters long!")
        @field:Max(value = 1000, message = "Agents description must be less than 1001 characters long!")
        val agentDescription: String?,
        @JsonProperty("profilePicture")
        val profilePicture: ProfilePictureDTO?
)