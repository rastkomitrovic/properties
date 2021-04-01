package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class RentTypeDTO(
        @JsonProperty("rentTypeId")
        @field:NotNull(message = "Rent type's Id must not be null!")
        val rentTypeId: Long?,

        @JsonProperty("rentTypeName")
        @field:NotNull(message = "Rent type's name must not be null!")
        @field:NotBlank(message = "Rent type's name can't be empty!")
        @field:Length(min = 2, max = 50, message = "Rent type's name must have between 2 and 50 characters!")
        val rentTypeName: String?,

        @JsonProperty("rentTypeDescription")
        val rentTypeDescription: String?
)