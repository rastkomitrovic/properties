package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "profile_picture")
open class ProfilePicture(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "profile_picture_id", nullable = false, unique = true) open val profilePictureId: Long? = null,
        @Column(name = "profile_picture_url", nullable = false) open val profilePictureUrl: String? = null
) : BaseEntity()