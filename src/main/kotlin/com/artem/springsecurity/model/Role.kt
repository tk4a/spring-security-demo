package com.artem.springsecurity.model

import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role(
    private val permission: MutableSet<Permission>
) {
    USER(mutableSetOf(Permission.PERSON_READ)),
    ADMIN(mutableSetOf(
        Permission.PERSON_READ,
        Permission.PERSON_WRITE
    ));

    fun getAuthority() = permission
        .map { SimpleGrantedAuthority(it.permission) }
        .toSet()
}