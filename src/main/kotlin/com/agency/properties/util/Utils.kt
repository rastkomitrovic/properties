package com.agency.properties.util

import com.agency.properties.entity.BaseEntity
import org.hibernate.Hibernate

class Utils {
    companion object {
        fun <T : BaseEntity?> isLazyEntityInitialized(baseEntity: T): Boolean {
            return Hibernate.isInitialized(baseEntity)
        }
    }
}