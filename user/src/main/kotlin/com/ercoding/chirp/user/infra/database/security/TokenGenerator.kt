package com.ercoding.chirp.user.infra.database.security

import java.security.SecureRandom

object TokenGenerator {
    fun generateSecureToken(): String {
        val bytes = ByteArray(32) { 0 }

        val secureRandom = SecureRandom()
        secureRandom.nextBytes(bytes)

        return java.util.Base64.getUrlEncoder()
            .withoutPadding()
            .encodeToString(bytes)
    }
}