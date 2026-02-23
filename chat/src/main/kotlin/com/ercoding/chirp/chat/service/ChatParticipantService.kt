package com.ercoding.chirp.chat.service

import com.ercoding.chirp.chat.domain.models.ChatParticipant
import com.ercoding.chirp.chat.infra.database.mappers.toChatParticipant
import com.ercoding.chirp.chat.infra.database.mappers.toChatParticipantEntity
import com.ercoding.chirp.chat.infra.database.repositories.ChatParticipantRepository
import com.ercoding.chirp.domain.type.UserId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ChatParticipantService(
    private val chatParticipantRepository: ChatParticipantRepository
) {
    fun createChatParticipant(
        chatParticipant: ChatParticipant
    ) {
        chatParticipantRepository.save(
            chatParticipant.toChatParticipantEntity()
        )
    }

    fun findChatParticipantById(userId: UserId): ChatParticipant? {
        return chatParticipantRepository.findByIdOrNull(userId)?.toChatParticipant()
    }

    fun findChatParticipantByEmailOrUsername(
        query: String
    ): ChatParticipant? {
        val normalizedQuery = query.lowercase().trim()
        return chatParticipantRepository.findByEmailOrUsername(
            query = normalizedQuery
        )?.toChatParticipant()
    }
}