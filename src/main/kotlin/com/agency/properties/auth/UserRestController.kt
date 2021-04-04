package com.agency.properties.auth

import com.agency.properties.dto.AgentDTO
import com.agency.properties.service.AgentService
import com.agency.properties.util.AgencyException
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.util.*
import javax.validation.Valid

@RestController
class UserRestController @Autowired constructor(
        private val userAuthenticationService: UserAuthenticationService,
        private val agentService: AgentService
) {

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    fun register(@RequestBody @Valid agentDTO: AgentDTO): String {
        val agent = agentService.saveAgent(agentDTO)
        return userAuthenticationService.login(agent.agentUsername, agent.agentPassword).orElseThrow{throw AgencyException("Error while creating new user!")}
    }

    @Operation(summary = "Login as an user")
    @PostMapping("/public/login")
    fun login(@RequestParam("username") username:String, @RequestParam("password") password:String): String? {
        return userAuthenticationService.login(username,password).orElseThrow{throw AgencyException("Invalid username and/or password!")}
    }

    @Operation(summary = "Gets logged in user")
    @GetMapping("/current")
    fun getCurrentUser(@AuthenticationPrincipal user:User):User{
        return user
    }

    @Operation(summary = "Logs out.")
    @GetMapping("/logout")
    fun logout(@AuthenticationPrincipal user:User):Boolean{
        userAuthenticationService.logout(user)
        return true
    }

}