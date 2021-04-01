package com.agency.properties.repository

import com.agency.properties.entity.Agent
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AgentRepository : CrudRepository<Agent, Long> {


    @Query("select a from Agent a where a.agentUsername=:username")
    fun findByUsernameNotLoadLazyEntities(@Param("username") username: String): Optional<Agent>

    @Query("select a from Agent a join fetch a.profilePicture join fetch a.location properties where a.agentUsername=:username")
    fun findByUsernameLoadLazyEntities(@Param("username") username: String): Optional<Agent>

    @Query("select a.agentUsername from Agent a where a.agentUsername=:username")
    fun findUsername(@Param("username") username: String): Optional<String>

    @Query("select a from Agent a join fetch a.profilePicture join fetch a.location where a.agentId=:id")
    fun findByIdLoadLazyEntities(@Param("id") id: Long): Optional<Agent>
}