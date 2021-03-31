package com.agency.properties.dto

import com.agency.properties.util.MediaType
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class PropertyMediaDTO(
        @JsonProperty("propertyMediaId")
        @field:NotNull(message = "Property media's Id must not be null!")
        val propertyMediaId: Long?,

        @JsonProperty("propertyMediaType")
        @field:NotNull(message = "Property media's type must not be null!")
        @field:Length(min = 3, max = 15, message = "Property media's type must be between 3 and 15 characters!")
        val propertyMediaType: MediaType?,

        @JsonProperty("propertyMediaUrl")
        @field:NotNull(message = "Property media's url must not be null!")
        val propertyMediaUrl: String?
)