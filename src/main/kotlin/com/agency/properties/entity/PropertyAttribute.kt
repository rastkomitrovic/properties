package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "property_attribute")
data class PropertyAttribute(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "property_attribute_id", nullable = false, unique = true) val propertyAttributeId: Long,
        @Column(name = "property_attribute_name", nullable = false, unique = true) val propertyAttributeName: String,
        @Column(name = "property_attribute_description", nullable = false) val propertyAttributeDescription: String
)