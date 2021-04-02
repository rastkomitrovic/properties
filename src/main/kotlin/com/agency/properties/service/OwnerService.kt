package com.agency.properties.service

import com.agency.properties.dto.OwnerDTO
import com.agency.properties.mapper.OwnerMapper
import com.agency.properties.repository.OwnerRepository
import com.agency.properties.util.AgencyException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
@CacheConfig(cacheNames = [OwnerService.OWNERS_PAGING_SEARCH_CACHE, OwnerService.OWNERS_PAGING_CACHE, OwnerService.OWNERS_BY_ID_CACHE])
class OwnerService @Autowired constructor(
        private val ownerRepository: OwnerRepository,
        private val ownerMapper: OwnerMapper
) {

    private val log = LoggerFactory.getLogger(OwnerService::class.java)

    @CacheEvict(value = [OWNERS_PAGING_SEARCH_CACHE, OWNERS_PAGING_CACHE, OWNERS_BY_ID_CACHE], allEntries = true)
    fun saveOwner(ownerDTO: OwnerDTO): OwnerDTO {
        if (ownerDTO.ownerId != null && ownerRepository.existsById(ownerDTO.ownerId))
            throw AgencyException("Owner with the passed id:${ownerDTO.ownerId} already exists!")
        val owner = ownerMapper.mapToDto(ownerRepository.save(ownerMapper.mapToEntity(ownerDTO)))
        log.info("Evicting from caches: $OWNERS_PAGING_CACHE, $OWNERS_PAGING_SEARCH_CACHE and $OWNERS_BY_ID_CACHE all entries")
        return owner
    }

    @Caching(evict = [
        CacheEvict(value = [OWNERS_BY_ID_CACHE], key = "{#ownerDTO.ownerId,true}"),
        CacheEvict(value = [OWNERS_BY_ID_CACHE], key = "{#ownerDTO.ownerId,false}"),
        CacheEvict(value = [OWNERS_PAGING_CACHE], allEntries = true),
        CacheEvict(value = [OWNERS_PAGING_SEARCH_CACHE], allEntries = true)
    ])
    fun updateOwner(ownerDTO: OwnerDTO): OwnerDTO {
        if (ownerDTO.ownerId != null && !ownerRepository.existsById(ownerDTO.ownerId))
            throw AgencyException("Owner with the passed id:${ownerDTO.ownerId} doesn't exist!")
        val owner = ownerMapper.mapToDto(ownerRepository.save(ownerMapper.mapToEntity(ownerDTO)))
        log.info("Evicting from cache: $OWNERS_BY_ID_CACHE for keys {${ownerDTO.ownerId},true} and {${ownerDTO.ownerId},false} and from caches:$OWNERS_PAGING_CACHE and $OWNERS_PAGING_SEARCH_CACHE all entries")
        return owner
    }

    @Caching(evict = [
        CacheEvict(value = [OWNERS_BY_ID_CACHE], key = "{#id,true}"),
        CacheEvict(value = [OWNERS_BY_ID_CACHE], key = "{#id,false}"),
        CacheEvict(value = [OWNERS_PAGING_CACHE], allEntries = true),
        CacheEvict(value = [OWNERS_PAGING_SEARCH_CACHE], allEntries = true)
    ])
    fun deleteOwnerById(id: Long) {
        if (!ownerRepository.existsById(id))
            throw AgencyException("Owner with the passed id:${id} doesn't exist!")
        ownerRepository.deleteById(id)
        log.info("Evicting from cache: $OWNERS_BY_ID_CACHE for keys {$id,true} and {$id,false} and from caches:$OWNERS_PAGING_CACHE and $OWNERS_PAGING_SEARCH_CACHE all entries")
    }

    @Cacheable(value = [OWNERS_PAGING_CACHE], key = "{#page,#size,#sort,#loadLazyParams}")
    fun findNoSearchPaging(page: Int, size: Int, sort: String, loadLazyParams: Boolean): Page<OwnerDTO> {
        val page = when (loadLazyParams) {
            true -> ownerRepository.findByPagingLoadLazyEntities(PageRequest.of(page, size, Sort.by(sort))).map { ownerMapper.mapToDto(it) }
            else -> ownerRepository.findAll(PageRequest.of(page, size, Sort.by(sort))).map { ownerMapper.mapToDto(it) }
        }
        log.info("Storing into cache: $OWNERS_PAGING_SEARCH_CACHE for key:{$page,$size,$sort,$loadLazyParams}")
        return page
    }

    @Cacheable(value = [OWNERS_PAGING_SEARCH_CACHE], key = "{#page,#size,#sort,#param,#loadLazyParams}")
    fun findSearchPaging(page: Int, size: Int, sort: String, param: String, loadLazyParams: Boolean): Page<OwnerDTO> {
        val page = when (loadLazyParams) {
            true -> ownerRepository.findByParamPagingLoadLazyEntities(PageRequest.of(page, size, Sort.by(sort)), param).map { ownerMapper.mapToDto(it) }
            else -> ownerRepository.findByParamPagingNotLoadLazyEntities(PageRequest.of(page, size, Sort.by(sort)), param).map { ownerMapper.mapToDto(it) }
        }
        log.info("Storing into cache: $OWNERS_PAGING_SEARCH_CACHE for key:{$page,$size,$sort,$param,$loadLazyParams}")
        return page
    }

    @Cacheable(value = [OWNERS_BY_ID_CACHE], key = "{#id,#loadLazyParams}")
    fun findById(id: Long, loadLazyParams: Boolean): Optional<OwnerDTO> {
        val owner = when (loadLazyParams) {
            true -> ownerRepository.findByIdLoadLazyEntities(id)
            else -> ownerRepository.findById(id)
        }
        log.info("Storing into cache: $OWNERS_BY_ID_CACHE for key: {$id,$loadLazyParams}")
        return when (owner.isPresent) {
            true -> Optional.of(ownerMapper.mapToDto(owner.get()))
            else -> Optional.empty()
        }
    }

    companion object {
        const val OWNERS_BY_ID_CACHE = "ownersByIdCache"
        const val OWNERS_PAGING_CACHE = "ownersPagingCache"
        const val OWNERS_PAGING_SEARCH_CACHE = "ownersPagingSearchCache"
    }
}