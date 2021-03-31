package com.agency.properties.util

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ApiError(
        val httpStatus: HttpStatus,
        val dateTime: LocalDateTime,
        val message: String,
        val debugMessage: String,
        val errors: List<String>
)