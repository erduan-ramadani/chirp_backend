package com.ercoding.chirp.chat.api.controllers

import com.ercoding.chirp.api.util.requestUserId
import com.ercoding.chirp.chat.api.dto.AddParticipantToChatDto
import com.ercoding.chirp.chat.api.dto.ChatDto
import com.ercoding.chirp.chat.api.dto.CreateChatRequest
import com.ercoding.chirp.chat.api.mappers.toChatDto
import com.ercoding.chirp.chat.service.ChatService
import com.ercoding.chirp.domain.type.ChatId
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chat")
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping
    fun createChat(
        @Valid @RequestBody body: CreateChatRequest
    ): ChatDto {
        return chatService.createChat(
            creatorId = requestUserId,
            otherUserIds = body.otherUserIds.toSet()
        ).toChatDto()
    }

    @PostMapping("/{chatId}/add")
    fun addChatParticipants(
        @PathVariable chatId: ChatId,
        @Valid @RequestBody body: AddParticipantToChatDto
    ): ChatDto {
        return chatService.addParticipantsToChat(
            requestUserId = requestUserId,
            chatId = chatId,
            userIds = body.userIds.toSet()
        ).toChatDto()
    }

    @DeleteMapping("/{chatId}/leave")
    fun leaveChat(
        @PathVariable chatId: ChatId,
    ) {
        return chatService.removeParticipantFromChat(
            chatId = chatId,
            userId = requestUserId
        )
    }
}