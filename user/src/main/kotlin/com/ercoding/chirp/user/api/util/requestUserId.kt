package com.ercoding.chirp.user.api.util

import com.ercoding.chirp.domain.type.UserId
import com.ercoding.chirp.user.domain.exception.UnauthorizedException
import org.springframework.security.core.context.SecurityContextHolder

val requestUserId: UserId
    get() = SecurityContextHolder.getContext().authentication?.principal as? UserId
        ?: throw UnauthorizedException()