package com.agency.properties.dto

import com.agency.properties.util.MediaType
import com.fasterxml.jackson.annotation.JsonProperty

data class PropertyMediaDTO(
        @JsonProperty("propertyMediaId")
        val propertyMediaId: Long?,
        @JsonProperty("propertyMediaType")
        val propertyMediaType: MediaType?,
        @JsonProperty("propertyMediaUrl")
        val propertyMediaUrl: String?
)