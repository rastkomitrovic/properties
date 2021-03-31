package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "location")
open class Location(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "location_id", nullable = false, unique = true) open val locationId: Long? = null,
        @Column(name = "location_name", nullable = false, unique = true) open val locationName: String? = null,
        @Column(name = "location_description", nullable = true) open val locationDescription: String? = null
) : BaseEntity()