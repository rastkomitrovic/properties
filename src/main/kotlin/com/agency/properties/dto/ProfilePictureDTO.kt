package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ProfilePictureDTO(
        @JsonProperty("profilePictureId")
        val profilePictureId: Long?,
        @JsonProperty("profilePictureUrl")
        val profilePictureUrl: String?
)