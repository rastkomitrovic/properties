package com.agency.properties.repository

import com.agency.properties.entity.Agent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AgentRepository : CrudRepository<Agent, Long> {


    @Query("select a from Agent a where a.agentUsername=:username")
    fun findByUsernameWithoutPicture(@Param("username") username: String): Optional<Agent>

    @Query("select a from Agent a join fetch a.profilePicture where a.agentUsername=:username")
    fun findByUsernameWithPicture(@Param("username") username: String): Optional<Agent>

    @Query("select a.agentUsername from Agent a where a.agentUsername=:username")
    fun findUsername(@Param("username") username: String): Optional<String>
}