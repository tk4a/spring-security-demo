package com.artem.springsecurity.repository

import com.artem.springsecurity.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo : JpaRepository<User, UUID> {
    fun getByLogin(login: String?): User?
}