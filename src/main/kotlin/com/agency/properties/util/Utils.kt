package com.agency.properties.util

import com.agency.properties.entity.BaseEntity
import com.agency.properties.service.*
import org.hibernate.Hibernate

class Utils {
    companion object {
        fun <T : BaseEntity?> isLazyEntityInitialized(baseEntity: T): Boolean {
            return Hibernate.isInitialized(baseEntity)
        }

        fun getListOfCaches(): List<String> {
            return listOf(AgentService.AGENTS_EXISTING_BY_USERNAME_CACHE, AgentService.AGENTS_BY_ID_CACHE, AgentService.AGENTS_BY_USERNAME_CACHE,
                    CompanyDetailsService.COMPANY_DETAILS_CACHE, LocationService.LOCATIONS_BY_ID_CACHE, LocationService.LOCATIONS_ALL_CACHE,
                    OwnerService.OWNERS_BY_ID_CACHE, OwnerService.OWNERS_PAGING_SEARCH_CACHE, OwnerService.OWNERS_PAGING_CACHE,
                    PropertyAttributeService.PROPERTY_ATTRIBUTES_BY_ID_CACHE, PropertyAttributeService.PROPERTY_ATTRIBUTES_ALL_CACHE,
                    PropertyTypeService.PROPERTY_TYPES_BY_ID_CACHE, PropertyTypeService.PROPERTY_TYPES_ALL_CACHE,
                    RentTypeService.RENT_TYPES_BY_ID_CACHE, RentTypeService.RENT_TYPES_ALL_CACHE, RoleService.ROLES_ALL_CACHE)
        }
    }
}