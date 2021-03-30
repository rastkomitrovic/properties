package com.agency.properties.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CompanyDetailsDTO(
        @JsonProperty("companyDetailsId")
        val companyDetailsId: Long?,
        @JsonProperty("companyName")
        val companyName: String?,
        @JsonProperty("companyAddress")
        val companyAddress: String?,
        @JsonProperty("companyEmail")
        val companyEmail: String?,
        @JsonProperty("companyPhoneNumber")
        val companyPhoneNumber: String?,
        @JsonProperty("companyLogoUrl")
        val companyLogoUrl: String?
)