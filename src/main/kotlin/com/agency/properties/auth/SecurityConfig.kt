package com.agency.properties.auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.NegatedRequestMatcher
import org.springframework.security.web.util.matcher.OrRequestMatcher


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig @Autowired constructor(
        private val tokenAuthenticationProvider: TokenAuthenticationProvider
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.sessionManagement()
                ?.sessionCreationPolicy(STATELESS)
                ?.and()
                ?.exceptionHandling()
                ?.defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
                ?.and()
                ?.authenticationProvider(tokenAuthenticationProvider)
                ?.addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter::class.java)
                ?.authorizeRequests()
                ?.requestMatchers(PROTECTED_URLS)
                ?.authenticated()
                ?.and()
                ?.csrf()?.disable()
                ?.formLogin()?.disable()
                ?.httpBasic()?.disable()
                ?.logout()?.disable()

        //http?.authorizeRequests()?.anyRequest()?.permitAll()?.and()?.csrf()?.disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(tokenAuthenticationProvider)
    }

    override fun configure(web: WebSecurity?) {
        web?.ignoring()?.requestMatchers(PUBLIC_URLS)
    }

    @Bean
    fun forbiddenEntryPoint(): AuthenticationEntryPoint? {
        return HttpStatusEntryPoint(FORBIDDEN)
    }

    @Bean
    fun restAuthenticationFilter(): TokenAuthenticationFilter? {
        val filter = TokenAuthenticationFilter(PROTECTED_URLS)
        filter.setAuthenticationManager(authenticationManager())
        filter.setAuthenticationSuccessHandler(successHandler())
        return filter
    }

    @Bean
    fun successHandler(): SimpleUrlAuthenticationSuccessHandler? {
        val successHandler = SimpleUrlAuthenticationSuccessHandler()
        successHandler.setRedirectStrategy(NoRedirectStrategy())
        return successHandler
    }

    @Bean
    fun disableAutoRegistration(filter: TokenAuthenticationFilter?): FilterRegistrationBean<*>? {
        val registration = FilterRegistrationBean(filter)
        registration.isEnabled = false
        return registration
    }

    companion object {
        private val PUBLIC_URLS = OrRequestMatcher(AntPathRequestMatcher("/public/**"))
        private val PROTECTED_URLS = NegatedRequestMatcher(PUBLIC_URLS)
    }
}