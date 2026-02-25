package com.ercoding.chirp.chat.api.dto.ws

import com.ercoding.chirp.domain.type.UserId

data class ProfilePictureUpdateDto(
    val userId: UserId,
    val newUrl: String?
)
