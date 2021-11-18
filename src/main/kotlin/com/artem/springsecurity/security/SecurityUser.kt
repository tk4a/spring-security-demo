package com.artem.springsecurity.security

import com.artem.springsecurity.model.Status
import com.artem.springsecurity.model.User
import org.springframework.security.core.userdetails.User as SpringUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class SecurityUser(
    private val login: String,
    private val password: String,
    private val authorities: MutableList<GrantedAuthority>,
    private val isActive: Boolean
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        authorities

    override fun getPassword() = password

    override fun getUsername() = login

    override fun isAccountNonExpired() = isActive

    override fun isAccountNonLocked() = isActive

    override fun isCredentialsNonExpired() = isActive

    override fun isEnabled() = isActive

}


fun User.toUserDetails() = converter(
    username = this.login,
    password = this.password,
    enabled = this.status == Status.ACTIVE,
    accountNonExpired = this.status == Status.ACTIVE,
    credentialsNonExpired = this.status == Status.ACTIVE,
    accountNonLocked = this.status == Status.ACTIVE,
    authorities = this.role.getAuthority()
)

private fun converter(
    username: String,
    password: String,
    enabled: Boolean,
    accountNonExpired: Boolean,
    credentialsNonExpired: Boolean,
    accountNonLocked: Boolean,
    authorities: Set<GrantedAuthority>) =
    org.springframework.security.core.userdetails.User(
        username,
        password,
        enabled,
        accountNonExpired,
        credentialsNonExpired,
        accountNonLocked,
        authorities
    )