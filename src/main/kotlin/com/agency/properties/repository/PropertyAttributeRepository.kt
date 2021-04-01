package com.agency.properties.repository

import com.agency.properties.entity.PropertyAttribute
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PropertyAttributeRepository : CrudRepository<PropertyAttribute, Long> {
}