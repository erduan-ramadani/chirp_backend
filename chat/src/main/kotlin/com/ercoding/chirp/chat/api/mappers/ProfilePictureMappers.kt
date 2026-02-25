package com.ercoding.chirp.chat.api.mappers

import com.ercoding.chirp.chat.api.dto.PictureUploadResponse
import com.ercoding.chirp.chat.domain.models.ProfilePictureUploadCredentials

fun ProfilePictureUploadCredentials.toResponse(): PictureUploadResponse {
    return PictureUploadResponse(
        uploadUrl = uploadUrl,
        publicUrl = publicUrl,
        headers = headers,
        expiresAt = expiresAt
    )
}