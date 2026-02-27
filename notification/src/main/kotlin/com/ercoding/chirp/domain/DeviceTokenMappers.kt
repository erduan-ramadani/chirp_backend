package com.ercoding.chirp.domain

import com.ercoding.chirp.api.dto.DeviceTokenDto
import com.ercoding.chirp.api.dto.PlatformDto
import com.ercoding.chirp.domain.model.DeviceToken

fun DeviceToken.toDeviceTokenDto(): DeviceTokenDto {
    return DeviceTokenDto(
        userId = userId,
        token = token,
        createdAt = createdAt
    )
}

fun DeviceToken.Platform.toPlatformDto(): PlatformDto {
    return when (this) {
        DeviceToken.Platform.ANDROID -> PlatformDto.ANDROID
        DeviceToken.Platform.IOS -> PlatformDto.IOS
    }
}