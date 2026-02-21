package com.ercoding.chirp.user.domain.model

import com.ercoding.chirp.domain.type.UserId

data class User(
    val id: UserId,
    val username: String,
    val email: String,
    val hasEmailVerified: Boolean
)