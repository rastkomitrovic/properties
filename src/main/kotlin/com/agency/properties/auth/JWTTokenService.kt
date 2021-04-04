package com.agency.properties.auth

import io.jsonwebtoken.Clock
import org.springframework.stereotype.Service
import java.util.*

@Service
class JWTTokenService:Clock {
    override fun now(): Date {
        TODO("Not yet implemented")
    }


}