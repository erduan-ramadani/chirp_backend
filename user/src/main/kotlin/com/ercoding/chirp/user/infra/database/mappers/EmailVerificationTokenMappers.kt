package com.ercoding.chirp.user.infra.database.mappers

import com.ercoding.chirp.user.domain.model.EmailVerificationToken
import com.ercoding.chirp.user.infra.database.entities.EmailVerificationTokenEntity

fun EmailVerificationTokenEntity.toEmailVerificationToken(): EmailVerificationToken {
    return EmailVerificationToken(
        id = id,
        token = token,
        user = user.toUser()
    )
}