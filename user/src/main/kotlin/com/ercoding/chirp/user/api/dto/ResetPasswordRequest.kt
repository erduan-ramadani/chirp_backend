package com.ercoding.chirp.user.api.dto

import com.ercoding.chirp.user.api.util.Password
import jakarta.validation.constraints.NotBlank

data class ResetPasswordRequest(
    @field:NotBlank
    val token: String,
    @field:Password
    val newPassword: String
)
