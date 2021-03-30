package com.agency.properties.config

import com.agency.properties.util.AgencyException
import com.agency.properties.util.ApiError
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import javax.validation.ConstraintViolationException

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val errors = mutableListOf<String>()
        for (fieldError in ex.bindingResult.fieldErrors)
            errors.add("${fieldError.field} : ${fieldError.defaultMessage}")
        for (objectError in ex.bindingResult.globalErrors)
            errors.add("${objectError.objectName} : ${objectError.defaultMessage}")
        val apiError = ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Not all method argument were provided!", ex.localizedMessage, errors)
        return handleExceptionInternal(ex, apiError, headers, apiError.httpStatus, request)
    }

    override fun handleMissingServletRequestParameter(ex: MissingServletRequestParameterException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val error = ex.parameterName + " parameter is missing!"
        val apiError = ApiError(HttpStatus.BAD_REQUEST, LocalDateTime.now(), error, ex.localizedMessage, emptyList())
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

    override fun handleHttpRequestMethodNotSupported(ex: HttpRequestMethodNotSupportedException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val apiError = ApiError(HttpStatus.METHOD_NOT_ALLOWED, LocalDateTime.now(), "Method is not supported!", ex.localizedMessage, emptyList())
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

    @ExceptionHandler(AgencyException::class)
    fun handleAgencyException(ex: AgencyException, webRequest: WebRequest): ResponseEntity<Any> {
        val apiError = ApiError(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), ex.exceptionMessage,"Custom AgencyException thrown:${ex.stackTraceToString()}", emptyList())
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

    override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val apiError = ApiError(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(),"Request body not readable!", ex.localizedMessage, listOf())
        return ResponseEntity(apiError, HttpHeaders(), apiError.httpStatus)
    }

}