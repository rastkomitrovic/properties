package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "property_media")
data class PropertyMedia(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "property_media_id", nullable = false, unique = true) val propertyMediaId: Long,
        @Column(name = "property_media_type", nullable = false) val propertyMediaType: String,
        @Column(name = "property_media_url", nullable = false) val propertyMediaUrl: String
)
