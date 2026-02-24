package com.ercoding.chirp.chat.api.exception_handling

import com.ercoding.chirp.chat.domain.exception.ChatMessageNotFoundException
import com.ercoding.chirp.chat.domain.exception.ChatNotFoundException
import com.ercoding.chirp.chat.domain.exception.ChatParticipantNotFoundException
import com.ercoding.chirp.chat.domain.exception.InvalidChatSizeException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

class ChatExceptionHandler {

    @ExceptionHandler(
        ChatNotFoundException::class,
        ChatMessageNotFoundException::class,
        ChatParticipantNotFoundException::class
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onNotFound(e: Exception) = mapOf(
        "code" to "NOT_FOUND",
        "message" to e.message
    )

    @ExceptionHandler(
        InvalidChatSizeException::class,
    )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onInvalidChatSize(e: InvalidChatSizeException) = mapOf(
        "code" to "INVALID_CHAT_SIZE",
        "message" to e.message
    )
}