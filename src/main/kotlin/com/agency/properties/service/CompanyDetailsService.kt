package com.agency.properties.service

import com.agency.properties.dto.CompanyDetailsDTO
import com.agency.properties.mapper.CompanyDetailsMapper
import com.agency.properties.repository.CompanyDetailsRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
@CacheConfig(cacheNames = [CompanyDetailsService.COMPANY_DETAILS_CACHE])
class CompanyDetailsService @Autowired constructor(
        private val companyDetailsRepository: CompanyDetailsRepository,
        private val companyDetailsMapper: CompanyDetailsMapper
) {
    private val log = LoggerFactory.getLogger(CompanyDetailsService::class.java)

    fun saveCompanyDetails(companyDetailsDTO: CompanyDetailsDTO): CompanyDetailsDTO {
        return companyDetailsMapper.mapToDto(companyDetailsRepository.save(companyDetailsMapper.mapToEntity(companyDetailsDTO)))
    }

    @CacheEvict(value = [COMPANY_DETAILS_CACHE], allEntries = true)
    fun updateCompanyDetails(companyDetailsDTO: CompanyDetailsDTO): CompanyDetailsDTO {
        val companyDetails = companyDetailsMapper.mapToDto(companyDetailsRepository.save(companyDetailsMapper.mapToEntity(companyDetailsDTO)))
        log.info("Evicting cache $COMPANY_DETAILS_CACHE")
        return companyDetails
    }

    fun deleteCompanyDetailsById(id: Long) {
        if (companyDetailsRepository.existsById(id))
            companyDetailsRepository.deleteById(id)
    }

    fun findCompanyDetailsById(id: Long): Optional<CompanyDetailsDTO> {
        val agencyDetails = companyDetailsRepository.findById(id)
        return when (agencyDetails.isPresent) {
            true -> Optional.of(companyDetailsMapper.mapToDto(agencyDetails.get()))
            else -> Optional.empty()
        }
    }

    @Cacheable(value = [COMPANY_DETAILS_CACHE])
    fun findAllCompanyDetails(): List<CompanyDetailsDTO> {
        val companyDetails = companyDetailsMapper.mapListToDto(companyDetailsRepository.findAll())
        log.info("Storing in cache $COMPANY_DETAILS_CACHE")
        return companyDetails
    }

    companion object {
        const val COMPANY_DETAILS_CACHE: String = "companyDetailsCache"
    }
}