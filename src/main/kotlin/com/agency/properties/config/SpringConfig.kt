package com.agency.properties.config

import com.agency.properties.util.Utils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@Configuration
@EnableScheduling
class SpringConfig @Autowired constructor(
        private val cacheManager: CacheManager
) {

    @Scheduled(fixedRate = 86400000)
    fun evicting() {
        Utils.getListOfCaches().forEach { cacheManager.getCache(it)?.clear() }
    }
}


