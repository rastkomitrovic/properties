package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class ProfilePictureDTO(
        @JsonProperty("profilePictureId")
        @field:NotNull(message = "Profile picture Id must not be null!")
        val profilePictureId: Long?,

        @JsonProperty("profilePictureUrl")
        @field:NotNull(message = "Profile picture url must not be null!")
        val profilePictureUrl: String?
)