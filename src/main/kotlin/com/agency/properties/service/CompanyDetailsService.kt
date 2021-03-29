package com.agency.properties.service

import com.agency.properties.dto.CompanyDetailsDTO
import com.agency.properties.mapper.CompanyDetailsMapper
import com.agency.properties.repository.AgencyDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyDetailsService @Autowired constructor(
        private val agencyDetailsRepository: AgencyDetailsRepository,
        private val companyDetailsMapper: CompanyDetailsMapper
) {
    @CacheEvict(value = ["allDetails", "detailsById"], allEntries = true)
    fun saveCompanyDetails(companyDetailsDTO: CompanyDetailsDTO): CompanyDetailsDTO {
        return companyDetailsMapper.mapToDto(agencyDetailsRepository.save(companyDetailsMapper.mapToEntity(companyDetailsDTO)))
    }

    @CacheEvict(value = ["allDetails", "detailsById"], allEntries = true)
    fun updateCompanyDetails(companyDetailsDTO: CompanyDetailsDTO): CompanyDetailsDTO {
        return companyDetailsMapper.mapToDto(agencyDetailsRepository.save(companyDetailsMapper.mapToEntity(companyDetailsDTO)))
    }

    @CacheEvict(value = ["allDetails", "detailsById"], allEntries = true)
    fun deleteCompanyDetailsById(id: Long) {
        if (agencyDetailsRepository.existsById(id))
            agencyDetailsRepository.deleteById(id)
    }

    @Cacheable("detailsById")
    fun findCompanyDetailsById(id: Long): Optional<CompanyDetailsDTO> {
        println("findAgencyDetailsById: Not in cache")
        val agencyDetails = agencyDetailsRepository.findById(id)
        return when (agencyDetails.isPresent) {
            true -> Optional.of(companyDetailsMapper.mapToDto(agencyDetails.get()))
            else -> Optional.empty()
        }
    }

    @Cacheable("allDetails")
    fun findAllCompanyDetails(): List<CompanyDetailsDTO> {
        println("findAllAgencyDetails: Not in cache")
        return companyDetailsMapper.mapListToDto(agencyDetailsRepository.findAll())
    }
}