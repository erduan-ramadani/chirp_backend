//package com.ercoding.chirp.chat.api.websocket
//
//import com.ercoding.chirp.chat.service.ChatMessageService
//import com.ercoding.chirp.chat.service.ChatService
//import org.slf4j.LoggerFactory
//import org.springframework.http.HttpHeaders
//import org.springframework.stereotype.Component
//import org.springframework.web.socket.CloseStatus
//import org.springframework.web.socket.WebSocketSession
//import org.springframework.web.socket.handler.TextWebSocketHandler
//import tools.jackson.databind.ObjectMapper
//
//@Component
//class ChatWebSocketHandler(
//    private val chatMessageService: ChatMessageService,
//    private val objectMapper: ObjectMapper,
//    private val chatService: ChatService
//) : TextWebSocketHandler {
//
//    private val logger = LoggerFactory.getLogger(javaClass)
//
//    override fun afterConnectionEstablished(session: WebSocketSession) {
//        val authHeader = session
//            .handshakeHeaders
//            .getFirst(HttpHeaders.AUTHORIZATION)
//            ?: run {
//                logger.warn("Session ${session.id} was closed due to missing Authorization header")
//                session.close(CloseStatus.SERVER_ERROR.withReason("Authentication failed"))
//                return
//            }
//    }
//
//}
