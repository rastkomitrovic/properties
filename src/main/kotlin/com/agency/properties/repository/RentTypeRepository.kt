package com.agency.properties.repository

import com.agency.properties.entity.RentType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RentTypeRepository : CrudRepository<RentType, Long> {
}