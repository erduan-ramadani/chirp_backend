package com.ercoding.chirp.chat.domain.exception

import com.ercoding.chirp.domain.type.ChatMessageId

class ChatMessageNotFoundException(
    private val id: ChatMessageId
) : RuntimeException("Message with ID $id not found")