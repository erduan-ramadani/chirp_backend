package com.ercoding.chirp.chat.service

import com.ercoding.chirp.chat.domain.event.MessageDeletedEvent
import com.ercoding.chirp.chat.domain.exception.ChatMessageNotFoundException
import com.ercoding.chirp.chat.domain.exception.ChatNotFoundException
import com.ercoding.chirp.chat.domain.exception.ChatParticipantNotFoundException
import com.ercoding.chirp.chat.domain.models.ChatMessage
import com.ercoding.chirp.chat.infra.database.entities.ChatMessageEntity
import com.ercoding.chirp.chat.infra.database.mappers.toChatMessage
import com.ercoding.chirp.chat.infra.database.repositories.ChatMessageRepository
import com.ercoding.chirp.chat.infra.database.repositories.ChatParticipantRepository
import com.ercoding.chirp.chat.infra.database.repositories.ChatRepository
import com.ercoding.chirp.domain.events.chat.ChatEvent
import com.ercoding.chirp.domain.exception.ForbiddenException
import com.ercoding.chirp.domain.type.ChatId
import com.ercoding.chirp.domain.type.ChatMessageId
import com.ercoding.chirp.domain.type.UserId
import com.ercoding.chirp.infra.message_queue.EventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatMessageService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val eventPublisher: EventPublisher
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

        val savedMessage = chatMessageRepository.saveAndFlush(
            ChatMessageEntity(
                id = messageId,
                content = content.trim(),
                chatId = chatId,
                chat = chat,
                sender = sender
            )
        )

        eventPublisher.publish(
            event = ChatEvent.NewMessage(
                senderId = sender.userId,
                senderUsername = sender.username,
                recipientIds = chat.participants.map { it.userId }.toSet(),
                chatId = chatId,
                message = savedMessage.content
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

        applicationEventPublisher.publishEvent(
            MessageDeletedEvent(
                chatId = message.chatId,
                messageId = messageId
            )
        )
    }
}