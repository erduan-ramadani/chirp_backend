package com.ercoding.chirp.chat.api.dto.ws

import com.ercoding.chirp.domain.type.ChatId
import com.ercoding.chirp.domain.type.ChatMessageId

data class DeleteMessageDto(
    val chatId: ChatId,
    val messageId: ChatMessageId
)
