package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class AgentDTO(
        @JsonProperty("agentId")
        val agentId: Long?,
        @JsonProperty("agentFirstName")
        val agentFirstName: String?,
        @JsonProperty("agentLastName")
        val agentLastName: String?,
        @JsonProperty("agentUsername")
        val agentUsername: String?,
        @JsonProperty("agentPassword")
        val agentPassword: String?,
        @JsonProperty("agentDateOfBirth")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
        val agentDateOfBirth: Date?,
        @JsonProperty("agentPhoneNumber")
        val agentPhoneNumber: String?,
        @JsonProperty("agentEmail")
        val agentEmail: String?,
        @JsonProperty("agentAddress")
        val agentAddress: String?,
        @JsonProperty("agentIdNumber")
        val agentIdNumber: String?,
        @JsonProperty("agentDescription")
        val agentDescription: String?,
        @JsonProperty("profilePicture")
        val profilePicture: ProfilePictureDTO?
)