package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "property_type")
open class PropertyType(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "property_type_id", nullable = false, unique = true) open val propertyTypeId: Long? = null,
        @Column(name = "property_type_name", nullable = false, unique = true) open val propertyTypeName: String? = null,
        @Column(name = "property_type_description", nullable = true, unique = true) open val propertyTypeDescription: String? = null
) : BaseEntity()