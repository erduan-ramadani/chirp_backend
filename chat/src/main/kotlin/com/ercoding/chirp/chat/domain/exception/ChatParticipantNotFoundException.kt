package com.ercoding.chirp.chat.domain.exception

import com.ercoding.chirp.domain.type.UserId

class ChatParticipantNotFoundException(
    private val id: UserId
) : RuntimeException(
    "The chat participant with the ID $id was not found."
)