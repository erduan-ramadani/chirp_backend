package com.ercoding.chirp.chat.infra.database.mappers

import com.ercoding.chirp.chat.domain.models.Chat
import com.ercoding.chirp.chat.domain.models.ChatMessage
import com.ercoding.chirp.chat.domain.models.ChatParticipant
import com.ercoding.chirp.chat.infra.database.entities.ChatEntity
import com.ercoding.chirp.chat.infra.database.entities.ChatParticipantEntity

fun ChatEntity.toChat(lastMessage: ChatMessage? = null): Chat {
    return Chat(
        id = id!!,
        participants = participants.map {
            it.toChatParticipant()
        }.toSet(),
        creator = creator.toChatParticipant(),
        lastActivityAt = lastMessage?.createdAt ?: createdAt,
        createdAt = createdAt,
        lastMessage = lastMessage
    )
}

fun ChatParticipantEntity.toChatParticipant(): ChatParticipant {
    return ChatParticipant(
        userId = userId,
        username = username,
        email = email,
        profilePictureUrl = profilePictureUrl
    )
}