package com.ercoding.chirp.chat.domain.exception

class InvalidProfilePictureException(
    override val message: String? = null
) : RuntimeException(
    message ?: "Invalid profile picture data"
)