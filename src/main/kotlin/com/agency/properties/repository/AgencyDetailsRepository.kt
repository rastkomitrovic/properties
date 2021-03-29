package com.agency.properties.repository

import com.agency.properties.entity.CompanyDetails
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyDetailsRepository : CrudRepository<CompanyDetails, Long> {
}