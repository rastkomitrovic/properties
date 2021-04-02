package com.agency.properties.config

import com.agency.properties.service.AgentService
import com.agency.properties.service.CompanyDetailsService
import com.agency.properties.service.LocationService
import com.agency.properties.service.OwnerService
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.stereotype.Component

/*@Component
class CachingConfig : CacheManagerCustomizer<ConcurrentMapCacheManager> {
    override fun customize(cacheManager: ConcurrentMapCacheManager?) {
        cacheManager?.setCacheNames(listOf(CompanyDetailsService.COMPANY_DETAILS_CACHE,
                AgentService.AGENTS_BY_USERNAME_CACHE,
                AgentService.AGENTS_BY_ID_CACHE,
                LocationService.LOCATIONS_BY_ID_CACHE,
                LocationService.LOCATIONS_ALL_CACHE,
                OwnerService.OWNERS_PAGING_SEARCH_CACHE,
                OwnerService.OWNERS_PAGING_CACHE,
                OwnerService.OWNERS_BY_ID_CACHE))
    }
}*/