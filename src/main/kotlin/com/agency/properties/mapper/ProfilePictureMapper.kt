package com.agency.properties.mapper

import com.agency.properties.dto.ProfilePictureDTO
import com.agency.properties.entity.ProfilePicture
import org.springframework.stereotype.Service

@Service
class ProfilePictureMapper {

    fun mapToDto(profilePicture: ProfilePicture): ProfilePictureDTO {
        return ProfilePictureDTO(
                profilePictureId = profilePicture.profilePictureId,
                profilePictureUrl = profilePicture.profilePictureUrl
        )
    }

    fun mapToEntity(profilePictureDTO: ProfilePictureDTO): ProfilePicture {
        return ProfilePicture(
                profilePictureId = profilePictureDTO.profilePictureId,
                profilePictureUrl = profilePictureDTO.profilePictureUrl
        )
    }
}