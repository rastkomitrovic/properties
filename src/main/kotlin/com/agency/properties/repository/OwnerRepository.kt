package com.agency.properties.repository

import com.agency.properties.dto.OwnerDTO
import com.agency.properties.entity.Owner
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface OwnerRepository : PagingAndSortingRepository<Owner, Long> {

    @Query("select o from Owner o join fetch o.addedByAgent join fetch o.location",
            countQuery = "select count(o) from Owner o")
    fun findByPagingLoadLazyEntities(pageable: Pageable): Page<Owner>

    @Query("select o from Owner o where concat(o.ownerFirstName,' ',o.ownerLastName) like concat('%',:param,'%') or o.ownerEmail like concat('%',:param,'%') or o.ownerPhoneNumber like concat('%',:param,'%') or o.ownerAddress like concat('%',:param,'%') ")
    fun findByParamPagingNotLoadLazyEntities(pageable: Pageable, @Param("param") param: String): Page<Owner>

    @Query("select o from Owner o join fetch o.addedByAgent join fetch o.location where concat(o.ownerFirstName,' ',o.ownerLastName) like concat('%',:param,'%') or o.ownerEmail like concat('%',:param,'%') or o.ownerPhoneNumber like concat('%',:param,'%') ",
            countQuery = "select o from Owner o where concat(o.ownerFirstName,' ',o.ownerLastName) like concat('%',:param,'%') or o.ownerEmail like concat('%',:param,'%') or o.ownerPhoneNumber like concat('%',:param,'%')")
    fun findByParamPagingLoadLazyEntities(pageable: Pageable, @Param("param") param: String): Page<Owner>

    @Query("select o from Owner o join fetch o.addedByAgent join fetch o.location where o.ownerId=:id")
    fun findByIdLoadLazyEntities(@Param("id") id: Long): Optional<Owner>
}