package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import kotlin.math.max

data class CompanyDetailsDTO(
        @JsonProperty("companyDetailsId")
        @field:NotNull(message = "Company's details Id must not be null!")
        val companyDetailsId: Long?,

        @JsonProperty("companyName")
        @field:NotNull(message = "Company's name must not be null!")
        @field:NotBlank(message = "Company's name can't be empty!")
        @field:Length(min = 2, max = 100, message = "Company's name must be between 2 and 100 characters!")
        val companyName: String?,

        @JsonProperty("companyAddress")
        @field:NotNull(message = "Company's address must not be null!")
        @field:NotBlank(message = "Company's address can't be empty!")
        @field:Length(min = 5, max = 85, message = "Company's address must have between 5 and 85 characters!")
        val companyAddress: String?,

        @JsonProperty("companyEmail")
        @field:NotNull(message = "Company's email must not be null!")
        @field:NotBlank(message = "Company's email can't be empty!")
        @field:Email(message = "Company's email must be in correct format (mailAddres@domain.something)!")
        val companyEmail: String?,

        @JsonProperty("companyPhoneNumber")
        @field:NotNull(message = "Company's phone number must not be null!")
        @field:NotBlank(message = "Company's phone number can't be empty!")
        @field:Length(min = 5, max = 50, message = "Company's phone number must have between 5 and 50 characters!")
        val companyPhoneNumber: String?,

        @JsonProperty("companyLogoUrl")
        @field:NotBlank(message = "Company's logo can't be empty!")
        @field:NotNull(message = "Company's logo url must not be null!")
        val companyLogoUrl: String?
)