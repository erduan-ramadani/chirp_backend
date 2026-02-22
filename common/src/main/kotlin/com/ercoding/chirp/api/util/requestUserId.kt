package com.ercoding.chirp.api.util

import com.ercoding.chirp.domain.exception.UnauthorizedException
import com.ercoding.chirp.domain.type.UserId
import org.springframework.security.core.context.SecurityContextHolder


val requestUserId: UserId
    get() = SecurityContextHolder.getContext().authentication?.principal as? UserId
        ?: throw UnauthorizedException()