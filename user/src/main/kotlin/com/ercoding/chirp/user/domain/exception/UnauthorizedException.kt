package com.ercoding.chirp.user.domain.exception

class UnauthorizedException() : RuntimeException("Missing auth details")