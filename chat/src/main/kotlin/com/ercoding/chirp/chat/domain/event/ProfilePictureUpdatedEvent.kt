package com.ercoding.chirp.chat.domain.event

import com.ercoding.chirp.domain.type.UserId

data class ProfilePictureUpdatedEvent(
    val userId: UserId,
    val newUrl: String?
)