package com.ercoding.chirp.chat.domain.event

import com.ercoding.chirp.domain.type.ChatId
import com.ercoding.chirp.domain.type.UserId

data class ChatParticipantsJoinedEvent(
    val chatId: ChatId,
    val userIds: Set<UserId>
)
