package com.ercoding.chirp.user.domain.exception

class UserAlreadyExistsException : RuntimeException(
    "A user with this email or username already exists."
)
