package com.ercoding.chirp.chat.api.controllers

import com.ercoding.chirp.api.util.requestUserId
import com.ercoding.chirp.chat.api.dto.AddParticipantToChatDto
import com.ercoding.chirp.chat.api.dto.ChatDto
import com.ercoding.chirp.chat.api.dto.ChatMessageDto
import com.ercoding.chirp.chat.api.dto.CreateChatRequest
import com.ercoding.chirp.chat.api.mappers.toChatDto
import com.ercoding.chirp.chat.service.ChatService
import com.ercoding.chirp.domain.type.ChatId
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/api/chat")
class ChatController(
    private val chatService: ChatService,
) {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 20
    }

    @GetMapping("/{chatId}/messages")
    fun getMessagesForChat(
        @PathVariable("chatId") chatId: ChatId,
        @RequestParam("before", required = false) before: Instant? = null,
        @RequestParam("pageSize", required = false) pageSize: Int = DEFAULT_PAGE_SIZE
    ): List<ChatMessageDto> {
        return chatService.getChatMessages(
            chatId = chatId,
            before = before,
            pageSize = pageSize
        )
    }

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