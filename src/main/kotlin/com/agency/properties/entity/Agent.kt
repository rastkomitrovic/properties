package com.agency.properties.entity

import java.util.*
import javax.persistence.*
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "agent")
open class Agent(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "agent_id", nullable = false, unique = true) open val agentId: Long? = null,
        @Column(name = "agent_first_name", nullable = false) open val agentFirstName: String? = null,
        @Column(name = "agent_last_name", nullable = false) open val agentLastName: String? = null,
        @Column(name = "agent_username", nullable = false, unique = true) open val agentUsername: String? = null,
        @Column(name = "agent_password", nullable = false) open val agentPassword: String? = null,
        @Column(name = "agent_date_of_birth", nullable = false) open val agentDateOfBirth: Date? = null,
        @Column(name = "agent_phone_number", nullable = false) open val agentPhoneNumber: String? = null,
        @Column(name = "agent_email", nullable = false) open val agentEmail: String? = null,
        @Column(name = "agent_address", nullable = false) open val agentAddress: String? = null,
        @Column(name = "agent_id_number", nullable = false, unique = true) open val agentIdNumber: String? = null,
        @Column(name = "agent_description", nullable = false) open val agentDescription: String? = null,
        @ManyToOne(fetch = FetchType.EAGER) @JoinColumn(name = "role_id", nullable = false) open val agentRole: Role? = null,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "location_id", nullable = false) open val location: Location? = null,
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true) @JoinColumn(name = "profile_picture_id", nullable = true) open val profilePicture: ProfilePicture?
) : BaseEntity()