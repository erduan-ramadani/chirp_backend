package com.ercoding.chirp.chat.api.dto.ws

import com.ercoding.chirp.domain.type.ChatId

data class ChatParticipantsChangedDto(
    val chatId: ChatId
)
