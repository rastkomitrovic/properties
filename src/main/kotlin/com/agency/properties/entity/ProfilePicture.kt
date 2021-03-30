package com.agency.properties.entity

import javax.persistence.*

@Entity
@Table(name = "profile_picture")
data class ProfilePicture(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "profile_picture_id", nullable = false, unique = true) val profilePictureId: Long,
        @Column(name = "profile_picture_url", nullable = false) val profilePictureUrl: String
)