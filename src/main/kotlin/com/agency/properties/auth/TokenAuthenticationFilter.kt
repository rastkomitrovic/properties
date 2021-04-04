package com.agency.properties.auth

import com.google.common.net.HttpHeaders.AUTHORIZATION
import java.util.Optional.ofNullable
import org.apache.commons.lang3.StringUtils.removeStart
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.stereotype.Component
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TokenAuthenticationFilter(private val requestMatcher: RequestMatcher) : AbstractAuthenticationProcessingFilter(requestMatcher) {


    override fun attemptAuthentication(p0: HttpServletRequest?, p1: HttpServletResponse?): Authentication {
        val param = ofNullable(p0?.getHeader(AUTHORIZATION)).orElse(p0?.getParameter("t"))

        val token = ofNullable(param).map { removeStart(it, BEARER) }.map { it.trim() }.orElseThrow { throw BadCredentialsException("Missing authentication token") }

        val auth = UsernamePasswordAuthenticationToken(token,token)
        return authenticationManager.authenticate(auth)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        super.successfulAuthentication(request, response, chain, authResult)
        chain?.doFilter(request,response)
    }

    companion object {
        private const val BEARER = "Bearer"
    }
}