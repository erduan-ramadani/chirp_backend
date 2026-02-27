package com.ercoding.chirp.infra.mappers

import com.ercoding.chirp.domain.model.DeviceToken
import com.ercoding.chirp.infra.database.DeviceTokenEntity

fun DeviceTokenEntity.toDeviceToken(): DeviceToken {
    return DeviceToken(
        userId = userId,
        token = token,
        platform = platform.toPlatform(),
        id = id,
        createdAt = createdAt
    )
}