package com.agency.properties.auth

import java.util.*

interface UserAuthenticationService {

    fun login(username: String?, password: String?): Optional<String>

    fun findByToken(token: String): Optional<User>

    fun logout(user: User)
}