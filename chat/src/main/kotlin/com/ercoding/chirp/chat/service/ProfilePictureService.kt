package com.ercoding.chirp.chat.service

import com.ercoding.chirp.chat.domain.event.ProfilePictureUpdatedEvent
import com.ercoding.chirp.chat.domain.exception.ChatParticipantNotFoundException
import com.ercoding.chirp.chat.domain.models.ProfilePictureUploadCredentials
import com.ercoding.chirp.chat.infra.database.repositories.ChatParticipantRepository
import com.ercoding.chirp.chat.infra.storage.SupabaseStorageService
import com.ercoding.chirp.domain.type.UserId
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProfilePictureService(
    private val supabaseStorageService: SupabaseStorageService,
    private val chatParticipantRepository: ChatParticipantRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) {
    private val logger = LoggerFactory.getLogger(ProfilePictureService::class.java)

    fun generateUploadCredentials(
        userId: UserId,
        mimeType: String,
    ): ProfilePictureUploadCredentials {
        return supabaseStorageService.generateSignedUploadUrl(
            userId = userId,
            mimeType = mimeType
        )
    }

    @Transactional
    fun deleteProfilePicture(userId: UserId) {
        val participant = chatParticipantRepository.findByIdOrNull(userId)
            ?: throw ChatParticipantNotFoundException(userId)

        participant.profilePictureUrl?.let { url ->
            chatParticipantRepository.save(
                participant.apply {
                    profilePictureUrl = null
                }
            )
            supabaseStorageService.deleteFile(url)

            applicationEventPublisher.publishEvent(
                ProfilePictureUpdatedEvent(
                    userId = userId,
                    newUrl = null
                )
            )
        }
    }

    @Transactional
    fun confirmProfilePictureUpload(userId: UserId, publicUrl: String) {
        val participant = chatParticipantRepository.findByIdOrNull(userId)
            ?: throw ChatParticipantNotFoundException(userId)

        val oldUrl = participant.profilePictureUrl

        chatParticipantRepository.save(
            participant.apply {
                profilePictureUrl = publicUrl
            }
        )

        try {
            oldUrl?.let {
                supabaseStorageService.deleteFile(oldUrl)
            }
        } catch (e: Exception) {
            logger.warn("Deleting old profile picture for $userId failed", e)
        }

        applicationEventPublisher.publishEvent(
            ProfilePictureUpdatedEvent(
                userId = userId,
                newUrl = publicUrl
            )
        )
    }
}