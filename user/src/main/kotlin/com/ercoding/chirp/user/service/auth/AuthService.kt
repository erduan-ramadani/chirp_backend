package com.ercoding.chirp.user.service.auth

import com.ercoding.chirp.user.domain.exception.UserAlreadyExistsException
import com.ercoding.chirp.user.domain.model.User
import com.ercoding.chirp.user.infra.database.entities.UserEntity
import com.ercoding.chirp.user.infra.database.mappers.toUser
import com.ercoding.chirp.user.infra.database.repositories.UserRepository
import com.ercoding.chirp.user.infra.database.security.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun register(email: String, username: String, password: String): User {
        val user = userRepository.findByEmailOrUsername(
            email = email.trim(),
            username = username.trim()
        )
        if (user != null) {
            throw UserAlreadyExistsException()
        }

        val savedUser = userRepository.save(
            UserEntity(
                email = email.trim(),
                username = username.trim(),
                hashedPassword = passwordEncoder.encode(password)
            )
        ).toUser()
        return savedUser
    }
}