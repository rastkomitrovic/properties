package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "property_attribute")
open class PropertyAttribute(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "property_attribute_id", nullable = false, unique = true) open val propertyAttributeId: Long? = null,
        @Column(name = "property_attribute_name", nullable = false, unique = true) open val propertyAttributeName: String? = null,
        @Column(name = "property_attribute_description", nullable = false) open val propertyAttributeDescription: String? = null
) : BaseEntity()