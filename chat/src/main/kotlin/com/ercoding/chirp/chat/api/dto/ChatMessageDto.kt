package com.ercoding.chirp.chat.api.dto

import com.ercoding.chirp.domain.type.ChatId
import com.ercoding.chirp.domain.type.ChatMessageId
import com.ercoding.chirp.domain.type.UserId
import java.time.Instant

data class ChatMessageDto(
    val id: ChatMessageId,
    val chatId: ChatId,
    val content: String,
    val createdAt: Instant,
    val senderId: UserId
)
