package com.ercoding.chirp.chat.api.controllers

import com.ercoding.chirp.api.util.requestUserId
import com.ercoding.chirp.chat.service.ChatMessageService
import com.ercoding.chirp.domain.type.ChatMessageId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/messages")
class ChatMessageController(private val chatMessageService: ChatMessageService) {

    @DeleteMapping("/{messageId}")
    fun deleteMessage(
        @PathVariable("messageId") messageId: ChatMessageId
    ) {
        chatMessageService.deleteMessage(messageId, requestUserId)
    }
}