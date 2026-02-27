package com.ercoding.chirp.service

import com.ercoding.chirp.domain.exception.InvalidDeviceTokenException
import com.ercoding.chirp.domain.model.DeviceToken
import com.ercoding.chirp.domain.model.PushNotification
import com.ercoding.chirp.domain.type.ChatId
import com.ercoding.chirp.domain.type.UserId
import com.ercoding.chirp.infra.database.DeviceTokenEntity
import com.ercoding.chirp.infra.database.DeviceTokenRepository
import com.ercoding.chirp.infra.mappers.toDeviceToken
import com.ercoding.chirp.infra.mappers.toPlatformEntity
import com.ercoding.chirp.push_notification.FirebasePushNotificationService
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class PushNotificationService(
    private val deviceTokenRepository: DeviceTokenRepository,
    private val firebasePushNotificationService: FirebasePushNotificationService
) {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun registerDevice(
        userId: UserId,
        token: String,
        platform: DeviceToken.Platform
    ): DeviceToken {
        val existing = deviceTokenRepository.findByToken(token)

        val trimmedToken = token.trim()
        if (existing == null && !firebasePushNotificationService.isValidToken(trimmedToken)) {
            throw InvalidDeviceTokenException()
        }

        val entity = if (existing != null) {
            deviceTokenRepository.save(
                existing.apply {
                    this.userId = userId
                }
            )
        } else {
            deviceTokenRepository.save(
                DeviceTokenEntity(
                    userId = userId,
                    token = trimmedToken,
                    platform = platform.toPlatformEntity()
                )
            )
        }
        return entity.toDeviceToken()
    }

    @Transactional
    fun unregisterToken(token: String) {
        deviceTokenRepository.deleteByToken(token.trim())
    }

    fun sendNewMessageNotifications(
        recipientUserIds: List<UserId>,
        senderUserId: UserId,
        senderUsername: String,
        message: String,
        chatId: ChatId
    ) {
        val deviceTokens = deviceTokenRepository.findByUserIdIn(recipientUserIds)
        if (deviceTokens.isEmpty()) {
            logger.info("No device tokens found for $recipientUserIds")
            return
        }

        val recipients = deviceTokens
            .filter { it.userId != senderUserId }
            .map { it.toDeviceToken() }

        val notification = PushNotification(
            title = "New message from $senderUsername",
            recipients = recipients,
            message = message,
            chatId = chatId,
            data = mapOf(
                "chatId" to chatId.toString(),
                "type" to "new_message"
            )
        )
        firebasePushNotificationService.sendNotification(notification)
    }
}