package com.ercoding.chirp.user.api.mappers

import com.ercoding.chirp.user.api.dto.AuthenticatedUserDto
import com.ercoding.chirp.user.api.dto.UserDto
import com.ercoding.chirp.user.domain.model.AuthenticatedUser
import com.ercoding.chirp.user.domain.model.User

fun AuthenticatedUser.toAuthenticatedUserDto(): AuthenticatedUserDto {
    return AuthenticatedUserDto(
        user = user.toUserDto(),
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        email = email,
        username = username,
        hasVerifiedEmail = hasEmailVerified
    )
}