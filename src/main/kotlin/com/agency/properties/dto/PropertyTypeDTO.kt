package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PropertyTypeDTO(
        @JsonProperty("propertyTypeId")
        @field:NotNull(message = "Property type's id must not be null!")
        val propertyTypeId: Long?,

        @JsonProperty("propertyTypeName")
        @field:NotNull(message = "Property type's name must not be null!")
        @field:NotBlank(message = "Property type's name can't be empty!")
        @field:Length(min = 2, max = 50, message = "Property type's name must have between 2 and 50 characters!")
        val propertyTypeName: String?,

        @JsonProperty("propertyTypeDescription")
        val propertyTypeDescription: String?
)
