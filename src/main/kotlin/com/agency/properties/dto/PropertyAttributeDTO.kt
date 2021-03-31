package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class PropertyAttributeDTO(
        @JsonProperty("propertyAttributeId")
        @field:NotNull(message = "Property attribute's id must not be null!")
        val propertyAttributeId: Long? = null,

        @JsonProperty("propertyAttributeName")
        @field:NotNull(message = "Property attribute's name must not be null!")
        @field:Length(min = 2, max = 30, message = "Property attribute's name must be between 2 and 30 characters!")
        val propertyAttributeName: String? = null,

        @JsonProperty("propertyAttributeDescription")
        val propertyAttributeDescription: String? = null
)
