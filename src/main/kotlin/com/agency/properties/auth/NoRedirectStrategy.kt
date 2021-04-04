package com.agency.properties.auth

import org.springframework.security.web.RedirectStrategy
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class NoRedirectStrategy:RedirectStrategy {
    override fun sendRedirect(p0: HttpServletRequest?, p1: HttpServletResponse?, p2: String?) {
        TODO("Not yet implemented")
    }
}