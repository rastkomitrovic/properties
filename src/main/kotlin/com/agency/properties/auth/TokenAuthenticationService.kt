package com.agency.properties.auth

import com.agency.properties.service.AgentService
import com.agency.properties.util.AgencyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenAuthenticationService @Autowired constructor(
        private val jwtTokenService: JWTTokenService,
        private val agentService: AgentService,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder
) : UserAuthenticationService {
    override fun login(username: String?, password: String?): Optional<String> {
        if (username == null || password == null)
            throw AgencyException("Username and/or password not provided!")
        val agent = agentService.findByUsername(username = username, loadLazyParams = false, mapPassword = true)
        if(agent.isPresent){

        }else
            throw AgencyException("Username and/or password not correct!")
    }

    override fun findByToken(token: String): Optional<User> {
        TODO("Not yet implemented")
    }

    override fun logout(user: User) {
        TODO("Not yet implemented")
    }
}