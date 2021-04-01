package com.agency.properties.repository

import com.agency.properties.entity.Location
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : CrudRepository<Location, Long> {
}