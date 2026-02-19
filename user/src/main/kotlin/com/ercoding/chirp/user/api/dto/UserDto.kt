package com.ercoding.chirp.user.api.dto

import com.ercoding.chirp.user.domain.model.UserId

data class UserDto(
    val id: UserId,
    val email: String,
    val username: String,
    val hasVerifiedEmail: Boolean
)
