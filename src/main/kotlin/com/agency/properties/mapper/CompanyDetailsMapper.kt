package com.agency.properties.mapper

import com.agency.properties.dto.CompanyDetailsDTO
import com.agency.properties.entity.CompanyDetails
import org.springframework.stereotype.Service

@Service
final class CompanyDetailsMapper {

    fun mapToDto(companyDetails: CompanyDetails): CompanyDetailsDTO {
        return CompanyDetailsDTO(
                companyDetailsId = companyDetails.companyDetailsId,
                companyName = companyDetails.companyName,
                companyAddress = companyDetails.companyAddress,
                companyEmail = companyDetails.companyEmail,
                companyPhoneNumber = companyDetails.companyPhoneNumber,
                companyLogoUrl = companyDetails.companyLogoUrl
        )
    }

    fun mapToEntity(companyDetailsDTO: CompanyDetailsDTO): CompanyDetails {
        return CompanyDetails(
                companyDetailsId = companyDetailsDTO.companyDetailsId,
                companyName = companyDetailsDTO.companyName,
                companyAddress = companyDetailsDTO.companyAddress,
                companyEmail = companyDetailsDTO.companyEmail,
                companyPhoneNumber = companyDetailsDTO.companyPhoneNumber,
                companyLogoUrl = companyDetailsDTO.companyLogoUrl)
    }

    fun mapListToDto(details: Iterable<CompanyDetails>): List<CompanyDetailsDTO> {
        return details.map { mapToDto(it) }
    }
}