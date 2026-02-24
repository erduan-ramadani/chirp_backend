package com.ercoding.chirp.chat.service

import com.ercoding.chirp.chat.domain.exception.ChatNotFoundException
import com.ercoding.chirp.chat.domain.exception.ChatParticipantNotFoundException
import com.ercoding.chirp.chat.domain.exception.ForbiddenException
import com.ercoding.chirp.chat.domain.exception.InvalidChatSizeException
import com.ercoding.chirp.chat.domain.models.Chat
import com.ercoding.chirp.chat.domain.models.ChatMessage
import com.ercoding.chirp.chat.infra.database.entities.ChatEntity
import com.ercoding.chirp.chat.infra.database.mappers.toChat
import com.ercoding.chirp.chat.infra.database.mappers.toChatMessage
import com.ercoding.chirp.chat.infra.database.repositories.ChatMessageRepository
import com.ercoding.chirp.chat.infra.database.repositories.ChatParticipantRepository
import com.ercoding.chirp.chat.infra.database.repositories.ChatRepository
import com.ercoding.chirp.domain.type.ChatId
import com.ercoding.chirp.domain.type.UserId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val chatMessageRepository: ChatMessageRepository
) {
    @Transactional
    fun createChat(
        creatorId: UserId,
        otherUserIds: Set<UserId>
    ): Chat {
        val otherParticipants = chatParticipantRepository.findByUserIdIn(
            userIds = otherUserIds
        )

        val allParticipants = (otherParticipants + creatorId)
        if (allParticipants.size < 2) {
            throw InvalidChatSizeException()
        }

        val creator = chatParticipantRepository.findByIdOrNull(creatorId)
            ?: throw ChatParticipantNotFoundException(creatorId)

        return chatRepository.save(
            ChatEntity(
                creator = creator,
                participants = setOf(creator) + otherParticipants
            )
        ).toChat(lastMessage = null)
    }

    @Transactional
    fun addParticipantsToChat(
        requestUserId: UserId,
        chatId: ChatId,
        userIds: Set<UserId>
    ): Chat {
        val chat = chatRepository.findByIdOrNull(chatId)
            ?: throw ChatNotFoundException()

        val isRequestingUserInChat = chat.participants.any {
            it.userId == requestUserId
        }

        if (!isRequestingUserInChat) {
            throw ForbiddenException()
        }

        val users = userIds.map { userId ->
            chatParticipantRepository.findByIdOrNull(userId)
                ?: throw ChatParticipantNotFoundException(userId)
        }

        val lastMessage = lastMessageForChat(chatId)
        val updatedChat = chatRepository.save(
            chat.apply {
                this.participants = chat.participants + users
            }
        ).toChat(lastMessage)

        return updatedChat
    }

    @Transactional
    fun removeParticipantFromChat(
        chatId: ChatId,
        userId: UserId
    ) {
        val chat = chatRepository.findByIdOrNull(chatId)
            ?: throw ChatNotFoundException()
        val participant = chat.participants.find { it.userId == userId }
            ?: throw ChatParticipantNotFoundException(userId)

        val newParticipantsSize = chat.participants.size - 1
        if (newParticipantsSize == 0) {
            chatRepository.deleteById(chatId)
            return
        }

        chatRepository.save(
            chat.apply {
                this.participants = chat.participants - participant
            }
        )
    }

    private fun lastMessageForChat(chatId: ChatId): ChatMessage? {
        return chatMessageRepository
            .findLatestMessageByChatIds(setOf(chatId))
            .firstOrNull()
            ?.toChatMessage()
    }
}