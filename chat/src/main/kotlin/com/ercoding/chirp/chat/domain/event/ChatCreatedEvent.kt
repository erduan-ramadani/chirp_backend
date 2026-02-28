package com.ercoding.chirp.chat.domain.event

import com.ercoding.chirp.domain.type.ChatId
import com.ercoding.chirp.domain.type.UserId

data class ChatCreatedEvent(
    val chatId: ChatId,
    val participantIds: List<UserId>
)
