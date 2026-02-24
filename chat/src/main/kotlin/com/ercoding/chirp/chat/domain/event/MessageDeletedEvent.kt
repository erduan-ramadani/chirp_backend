package com.ercoding.chirp.chat.domain.event

import com.ercoding.chirp.domain.type.ChatId
import com.ercoding.chirp.domain.type.ChatMessageId

data class MessageDeletedEvent(
    val chatId: ChatId,
    val messageId: ChatMessageId
)
