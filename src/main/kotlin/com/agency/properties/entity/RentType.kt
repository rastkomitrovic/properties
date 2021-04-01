package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "rent_type")
open class RentType(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "rent_type_id", nullable = false, unique = true) open val rentTypeId: Long? = null,
        @Column(name = "rent_type_name", nullable = false, unique = true) open val rentTypeName: String? = null,
        @Column(name = "rent_type_description", nullable = true) open val rentTypeDescription: String? = null
) : BaseEntity()