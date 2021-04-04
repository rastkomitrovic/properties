package com.agency.properties.auth

import java.util.*

interface UserAuthenticationService {

    fun login(username: String?, password: String?): Optional<String>

    fun findByToke(token: String): Optional<User>

    fun logout(user: User)
}