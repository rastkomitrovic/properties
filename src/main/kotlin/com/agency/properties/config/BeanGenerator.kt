package com.agency.properties.config


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
class BeanGenerator {

    @Bean
    fun getPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}