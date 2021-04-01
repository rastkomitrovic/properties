package com.agency.properties.service

import com.agency.properties.dto.OwnerDTO
import com.agency.properties.mapper.OwnerMapper
import com.agency.properties.repository.OwnerRepository
import com.agency.properties.util.AgencyException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class OwnerService @Autowired constructor(
        private val ownerRepository: OwnerRepository,
        private val ownerMapper: OwnerMapper
) {

    fun saveOwner(ownerDTO: OwnerDTO): OwnerDTO {
        if (ownerDTO.ownerId != null && ownerRepository.existsById(ownerDTO.ownerId))
            throw AgencyException("Owner with the passed id:${ownerDTO.ownerId} already exists!")
        return ownerMapper.mapToDto(ownerRepository.save(ownerMapper.mapToEntity(ownerDTO)))
    }

    fun updateOwner(ownerDTO: OwnerDTO): OwnerDTO {
        if (ownerDTO.ownerId != null && !ownerRepository.existsById(ownerDTO.ownerId))
            throw AgencyException("Owner with the passed id:${ownerDTO.ownerId} doesn't exist!")
        return ownerMapper.mapToDto(ownerRepository.save(ownerMapper.mapToEntity(ownerDTO)))
    }

    fun deleteOwnerById(id: Long) {
        if (!ownerRepository.existsById(id))
            throw AgencyException("Owner with the passed id:${id} doesn't exist!")
        ownerRepository.deleteById(id)
    }

    fun findNoSearchPaging(page: Int, size: Int, sort: String, loadLazyParams: Boolean): Page<OwnerDTO> {
        return when (loadLazyParams) {
            true -> ownerRepository.findByPagingLoadLazyEntities(PageRequest.of(page, size, Sort.by(sort))).map { ownerMapper.mapToDto(it) }
            else -> ownerRepository.findAll(PageRequest.of(page, size, Sort.by(sort))).map { ownerMapper.mapToDto(it) }
        }
    }

    fun findSearchPaging(page: Int, size: Int, sort: String, param: String, loadLazyParams: Boolean): Page<OwnerDTO> {
        return when (loadLazyParams) {
            true -> ownerRepository.findByParamPagingLoadLazyEntities(PageRequest.of(page, size, Sort.by(sort)), param).map { ownerMapper.mapToDto(it) }
            else -> ownerRepository.findByParamPagingNotLoadLazyEntities(PageRequest.of(page, size, Sort.by(sort)), param).map { ownerMapper.mapToDto(it) }
        }
    }
}