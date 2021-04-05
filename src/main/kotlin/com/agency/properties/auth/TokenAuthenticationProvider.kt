package com.agency.properties.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenAuthenticationProvider @Autowired constructor(
        private val userAuthenticationService: UserAuthenticationService
): AbstractUserDetailsAuthenticationProvider() {


    override fun additionalAuthenticationChecks(p0: UserDetails?, p1: UsernamePasswordAuthenticationToken?) {

    }

    override fun retrieveUser(p0: String?, p1: UsernamePasswordAuthenticationToken?): UserDetails {
        val token =p1?.credentials
        return Optional.ofNullable(token)
                .flatMap { userAuthenticationService.findByToken(it.toString()) }
                .orElseThrow{throw UsernameNotFoundException("Can't find user with authentication token: $token")}
    }
}