package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class LocationDTO(
        @JsonProperty("locationId")
        @field:NotNull(message = "Location's Id must not be null!")
        val locationId: Long?,

        @JsonProperty("locationName")
        @field:NotNull(message = "Location name must not be null!")
        @field:NotBlank(message = "Location's name can't be empty!")
        @field:Length(min = 3, max = 100, message = "Location must have between 3 and 100 characters!")
        val locationName: String?,

        @JsonProperty("locationDescription")
        val locationDescription: String?
)