package com.ercoding.chirp.chat.api.controllers

import com.ercoding.chirp.api.util.requestUserId
import com.ercoding.chirp.chat.api.dto.ChatDto
import com.ercoding.chirp.chat.api.dto.CreateChatRequest
import com.ercoding.chirp.chat.api.mappers.toChatDto
import com.ercoding.chirp.chat.service.ChatService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
}