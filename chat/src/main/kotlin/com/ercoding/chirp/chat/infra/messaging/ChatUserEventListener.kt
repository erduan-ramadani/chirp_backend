package com.ercoding.chirp.chat.infra.messaging

import com.ercoding.chirp.chat.domain.models.ChatParticipant
import com.ercoding.chirp.chat.service.ChatParticipantService
import com.ercoding.chirp.domain.events.user.UserEvent
import com.ercoding.chirp.infra.message_queue.MessageQueues
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class ChatUserEventListener(
    private val chatParticipantService: ChatParticipantService
) {
    private val logger = LoggerFactory.getLogger(javaClass)
    
    @RabbitListener(queues = [MessageQueues.CHAT_USER_EVENTS])
    fun handleUserEvent(event: UserEvent) {
        logger.info("Received user event: {}", event)
        when (event) {
            is UserEvent.Verified -> {
                chatParticipantService.createChatParticipant(
                    chatParticipant = ChatParticipant(
                        userId = event.userId,
                        username = event.username,
                        email = event.email,
                        profilePictureUrl = null
                    )
                )
            }

            else -> Unit
        }
    }
}