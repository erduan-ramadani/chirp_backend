package com.ercoding.chirp.user.domain.exception

class SamePasswordException: RuntimeException(
    "The new password should not be the same as the old one"
)