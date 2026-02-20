package com.ercoding.chirp.user.api.controller

import jakarta.validation.constraints.Email

data class EmailRequest(
    @field:Email
    val email: String
)
