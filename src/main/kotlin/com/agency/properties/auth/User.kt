package com.agency.properties.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class User(
        val userId: Long,
        private val agentUsername: String,
        private val agentPassword: String,
        private val customGrantedAuthority: CustomGrantedAuthority,
        val userFirstName: String,
        val userLastName: String
) : UserDetails {


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(customGrantedAuthority)
    }

    override fun getPassword(): String {
        return agentPassword
    }

    override fun getUsername(): String {
        return agentUsername
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}