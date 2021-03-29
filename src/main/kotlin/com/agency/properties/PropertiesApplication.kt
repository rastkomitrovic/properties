package com.agency.properties

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class PropertiesApplication

fun main(args: Array<String>) {
    runApplication<PropertiesApplication>(*args)
}
