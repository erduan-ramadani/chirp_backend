package com.ercoding.chirp.chat.service

import com.ercoding.chirp.domain.type.ChatId
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Component

@Component
class MessageCacheEvictionHelper {

    @CacheEvict(
        value = ["message"],
        key = "#chatId"
    )
    fun evictMessagesCache(chatId: ChatId) {
        // NO-OP: Let Spring handle the cache evict
    }
}