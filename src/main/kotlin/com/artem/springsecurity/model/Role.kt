package com.artem.springsecurity.model

import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role(
    private val permission: Set<Permission>
) {
    USER(setOf(Permission.PERSON_READ)),
    ADMIN(setOf(
        Permission.PERSON_READ,
        Permission.PERSON_WRITE
    ));

    fun getAuthority() = permission
        .map { SimpleGrantedAuthority(it.permission) }
        .toSet()
}