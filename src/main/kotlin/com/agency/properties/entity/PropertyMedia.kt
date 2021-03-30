package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "property_media")
open class PropertyMedia(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "property_media_id", nullable = false, unique = true) open val propertyMediaId: Long? = null,
        @Column(name = "property_media_type", nullable = false) open val propertyMediaType: String? = null,
        @Column(name = "property_media_url", nullable = false) open val propertyMediaUrl: String? = null
) : BaseEntity()
