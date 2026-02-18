package com.ercoding.chirp.user.infra.database.repositories

import com.ercoding.chirp.user.domain.model.UserId
import com.ercoding.chirp.user.infra.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, UserId> {
    fun findByEmail(email: String): UserEntity?
    fun findByEmailOrUsername(email: String, username: String): UserEntity?
}