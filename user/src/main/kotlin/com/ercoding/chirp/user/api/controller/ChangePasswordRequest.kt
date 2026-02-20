package com.ercoding.chirp.user.api.controller

import com.ercoding.chirp.user.api.util.Password
import jakarta.validation.constraints.NotBlank

data class ChangePasswordRequest(
    @field:NotBlank
    val oldPassword: String,
    @field:Password
    val newPassword: String
)
