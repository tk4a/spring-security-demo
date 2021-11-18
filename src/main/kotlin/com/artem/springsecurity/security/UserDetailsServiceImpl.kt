package com.artem.springsecurity.security

import com.artem.springsecurity.repository.UserRepo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    private val userRepo: UserRepo
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails =
        userRepo.getByLogin(username)?.run {
            this.toUserDetails()
        } ?: throw UsernameNotFoundException("user by $username not found")
}