package com.agency.properties.service

import com.agency.properties.dto.CompanyDetailsDTO
import com.agency.properties.mapper.CompanyDetailsMapper
import com.agency.properties.repository.CompanyDetailsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class CompanyDetailsService @Autowired constructor(
        private val companyDetailsRepository: CompanyDetailsRepository,
        private val companyDetailsMapper: CompanyDetailsMapper
) {
    @CacheEvict(value = ["allDetails", "detailsById"], allEntries = true)
    fun saveCompanyDetails(companyDetailsDTO: CompanyDetailsDTO): CompanyDetailsDTO {
        return companyDetailsMapper.mapToDto(companyDetailsRepository.save(companyDetailsMapper.mapToEntity(companyDetailsDTO)))
    }

    @CacheEvict(value = ["allDetails", "detailsById"], allEntries = true)
    fun updateCompanyDetails(companyDetailsDTO: CompanyDetailsDTO): CompanyDetailsDTO {
        return companyDetailsMapper.mapToDto(companyDetailsRepository.save(companyDetailsMapper.mapToEntity(companyDetailsDTO)))
    }

    @CacheEvict(value = ["allDetails", "detailsById"], allEntries = true)
    fun deleteCompanyDetailsById(id: Long) {
        if (companyDetailsRepository.existsById(id))
            companyDetailsRepository.deleteById(id)
    }

    @Cacheable("detailsById")
    fun findCompanyDetailsById(id: Long): Optional<CompanyDetailsDTO> {
        println("findAgencyDetailsById: Not in cache")
        val agencyDetails = companyDetailsRepository.findById(id)
        return when (agencyDetails.isPresent) {
            true -> Optional.of(companyDetailsMapper.mapToDto(agencyDetails.get()))
            else -> Optional.empty()
        }
    }

    @Cacheable("allDetails")
    fun findAllCompanyDetails(): List<CompanyDetailsDTO> {
        println("findAllAgencyDetails: Not in cache")
        return companyDetailsMapper.mapListToDto(companyDetailsRepository.findAll())
    }
}