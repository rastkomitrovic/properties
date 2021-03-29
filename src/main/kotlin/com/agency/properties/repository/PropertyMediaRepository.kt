package com.agency.properties.repository

import com.agency.properties.entity.PropertyMedia
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PropertyMediaRepository : CrudRepository<PropertyMedia, Long> {
}