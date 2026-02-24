package com.ercoding.chirp.chat.service

import com.ercoding.chirp.chat.domain.exception.ChatMessageNotFoundException
import com.ercoding.chirp.chat.domain.exception.ChatNotFoundException
import com.ercoding.chirp.chat.domain.exception.ChatParticipantNotFoundException
import com.ercoding.chirp.chat.domain.models.ChatMessage
import com.ercoding.chirp.chat.infra.database.entities.ChatMessageEntity
import com.ercoding.chirp.chat.infra.database.mappers.toChatMessage
import com.ercoding.chirp.chat.infra.database.repositories.ChatMessageRepository
import com.ercoding.chirp.chat.infra.database.repositories.ChatParticipantRepository
import com.ercoding.chirp.chat.infra.database.repositories.ChatRepository
import com.ercoding.chirp.domain.exception.ForbiddenException
import com.ercoding.chirp.domain.type.ChatId
import com.ercoding.chirp.domain.type.ChatMessageId
import com.ercoding.chirp.domain.type.UserId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatMessageService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val chatParticipantRepository: ChatParticipantRepository
) {

    @Transactional
    fun sendMessage(
        chatId: ChatId,
        senderId: UserId,
        content: String,
        messageId: ChatMessageId? = null
    ): ChatMessage {
        val chat = chatRepository.findChatById(chatId, senderId)
            ?: throw ChatNotFoundException()
        val sender = chatParticipantRepository.findByIdOrNull(senderId)
            ?: throw ChatParticipantNotFoundException(senderId)

        val savedMessage = chatMessageRepository.save(
            ChatMessageEntity(
                id = messageId,
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender
            )
        )

        return savedMessage.toChatMessage()
    }

    @Transactional
    fun deleteMessage(
        messageId: ChatMessageId,
        requestUserId: UserId
    ) {
        val message = chatMessageRepository.findByIdOrNull(messageId)
            ?: throw ChatMessageNotFoundException(messageId)

        if (message.sender.userId != requestUserId) {
            throw ForbiddenException()
        }

        chatMessageRepository.delete(message)
    }

}