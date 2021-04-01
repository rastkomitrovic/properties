package com.agency.properties.repository

import com.agency.properties.entity.Owner
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface OwnerRepository : PagingAndSortingRepository<Owner, Long> {

    @Query("select o from Owner o join fetch o.addedByAgent join fetch o.location")
    fun findByPagingLoadLazyEntities(pageable: Pageable): Page<Owner>

    @Query("select o from Owner o where concat(o.ownerFirstName,' ',o.ownerLastName) like concat('%',:param,'%') or o.ownerEmail like concat('%',:param,'%') or o.ownerPhoneNumber like concat('%',:param,'%') or o.ownerAddress like concat('%',:param,'%') ")
    fun findByParamPagingNotLoadLazyEntities(pageable: Pageable, param: String): Page<Owner>

    @Query("select o from Owner o join fetch o.addedByAgent join fetch o.location where concat(o.ownerFirstName,' ',o.ownerLastName) like concat('%',:param,'%') or o.ownerEmail like concat('%',:param,'%') or o.ownerPhoneNumber like concat('%',:param,'%') ")
    fun findByParamPagingLoadLazyEntities(pageable: Pageable, param: String): Page<Owner>
}