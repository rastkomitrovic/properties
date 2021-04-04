package com.agency.properties.auth

import org.springframework.security.core.GrantedAuthority

class CustomGrantedAuthority(
        private val role:String
):GrantedAuthority {
    override fun getAuthority(): String {
        return role
    }
}