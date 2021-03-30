package com.agency.properties.service

import com.agency.properties.entity.Agent
import com.agency.properties.repository.AgentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager
import javax.transaction.Transactional

@Service
@Transactional
class AgentService @Autowired constructor(
        private val agentRepository: AgentRepository,
        private val entityManager: EntityManager
) {

    fun findByUsernameWithoutPicture(username:String){
        agentRepository.findById(1)
        //return agentRepository.findByUsernameWithoutPicture(username)
        //return agent
        /*val criteriaBuilder =entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(Agent::class.java)
        val o = criteriaQuery.from(Agent::class.java)
        criteriaQuery.select(o)
        criteriaQuery.where(criteriaBuilder.equal(o.get<Long>("agentId"),id))
        val agent=entityManager.createQuery(criteriaQuery).singleResult
         */

    }

    fun findByUsernameWithPicture(){

    }

    fun existsByUsername(username:String):String{
        return agentRepository.existsByUsername(username)
    }
}