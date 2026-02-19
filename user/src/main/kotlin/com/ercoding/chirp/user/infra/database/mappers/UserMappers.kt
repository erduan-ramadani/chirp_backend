package com.ercoding.chirp.user.infra.database.mappers

import com.ercoding.chirp.user.domain.model.User
import com.ercoding.chirp.user.infra.database.entities.UserEntity

fun UserEntity.toUser(): User {
    return User(
        id = id!!,
        username = username,
        email = email,
        hasVerifiedEmail
    )
}