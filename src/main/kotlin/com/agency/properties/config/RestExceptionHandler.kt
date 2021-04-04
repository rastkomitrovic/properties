package com.agency.properties.config

import com.agency.properties.util.AgencyException
import com.agency.properties.util.ApiError
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.apache.coyote.Response
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.sql.SQLIntegrityConstraintViolationException
import java.time.LocalDateTime
import javax.validation.ConstraintViolationException

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400",content = [Content(mediaType = "application/json", schema = Schema(implementation = ApiError::class))])
    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errors = mutableListOf<String>()
        for (fieldError in ex.bindingResult.fieldErrors)
            errors.add("${fieldError.field} : ${fieldError.defaultMessage}")
        for (objectError in ex.bindingResult.globalErrors)
            errors.add("${objectError.objectName} : ${objectError.defaultMessage}")
        val apiError = ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Not all method argument were provided!", ex.localizedMessage, errors)
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400",content = [Content(mediaType = "application/json", schema = Schema(implementation = ApiError::class))])
    override fun handleMissingServletRequestParameter(ex: MissingServletRequestParameterException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val error = ex.parameterName + " parameter is missing!"
        val apiError = ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), error, ex.localizedMessage, emptyList())
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ApiResponse(responseCode = "400",content = [Content(mediaType = "application/json", schema = Schema(implementation = ApiError::class))])
    override fun handleHttpRequestMethodNotSupported(ex: HttpRequestMethodNotSupportedException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val apiError = ApiError(HttpStatus.METHOD_NOT_ALLOWED, LocalDateTime.now(), "Method is not supported!", ex.localizedMessage, emptyList())
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

    @ExceptionHandler(AgencyException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400",content = [Content(mediaType = "application/json", schema = Schema(implementation = ApiError::class))])
    fun handleAgencyException(ex: AgencyException, webRequest: WebRequest): ResponseEntity<Any> {
        val apiError = ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.exceptionMessage, "Custom AgencyException thrown:${ex.stackTraceToString()}", emptyList())
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400",content = [Content(mediaType = "application/json", schema = Schema(implementation = ApiError::class))])
    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val apiError = ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Request body not readable!", ex.localizedMessage, emptyList())
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ApiResponse(responseCode = "400",content = [Content(mediaType = "application/json", schema = Schema(implementation = ApiError::class))])
    fun handleSQLIntegrityException(ex: SQLIntegrityConstraintViolationException, webRequest: WebRequest): ResponseEntity<Any> {
        val apiError = ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "SQL EXCEPTION:${ex.message}", ex.stackTraceToString(), emptyList())
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

}