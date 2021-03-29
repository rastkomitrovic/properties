package com.agency.properties.dto

import com.agency.properties.util.MediaType

data class PropertyMediaDTO(
        val propertyMediaId: Long,
        val propertyMediaType: MediaType,
        val propertyMediaUrl: String
)