package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "role")
open class Role(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "role_id", nullable = false, unique = true) open val roleId: Long? = null,
        @Column(name = "role_name", nullable = false, unique = true) open val roleName: String? = null,
        @Column(name = "role_description", nullable = true) open val roleDescription: String? = null
) : BaseEntity()