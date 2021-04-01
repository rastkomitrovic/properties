package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "owner")
open class Owner(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "owner_id", nullable = false, unique = true) open val ownerId: Long? = null,
        @Column(name = "owner_first_name", nullable = false) open val ownerFirstName: String? = null,
        @Column(name = "owner_last_name", nullable = false) open val ownerLastName: String? = null,
        @Column(name = "owner_email", nullable = false) open val ownerEmail: String? = null,
        @Column(name = "owner_phone_number", nullable = false) open val ownerPhoneNumber: String? = null,
        @Column(name = "owner_address", nullable = false) open val ownerAddress: String? = null,
        @Column(name = "owner_description", nullable = true) open val ownerDescription: String? = null,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "agent_id", nullable = true) open val addedByAgent: Agent? = null,
        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "location_id", nullable = false) open val location: Location? = null
) : BaseEntity()