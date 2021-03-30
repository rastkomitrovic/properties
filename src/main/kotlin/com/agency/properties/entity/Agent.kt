package com.agency.properties.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.*
import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "agent")
data class Agent(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "agent_id", nullable = false, unique = true) val agentId: Long,
        @Column(name = "agent_first_name", nullable = false) val agentFirstName: String,
        @Column(name = "agent_last_name", nullable = false) val agentLastName: String,
        @Column(name = "agent_username", nullable = false, unique = true) val agentUsername:String,
        @Column(name = "agent_password", nullable = false) val agentPassword:String,
        @Column(name = "agent_date_of_birth", nullable = false) val agentDateOfBirth: Date,
        @Column(name = "agent_phone_number", nullable = false) val agentPhoneNumber: String,
        @Column(name = "agent_email", nullable = false) val agentEmail: String,
        @Column(name = "agent_address", nullable = false) val agentAddress: String,
        @Column(name = "agent_id_number", nullable = false) val agentIdNumber: String,
        @Column(name = "agent_description", nullable = false) val agentDescription: String,
        @OneToOne(cascade = [CascadeType.ALL],fetch = FetchType.LAZY) @JoinColumn(name = "profile_picture_id") @Fetch(FetchMode.JOIN) val profilePicture: ProfilePicture? = null
)