package com.artem.springsecurity.model

enum class Permission(
    val permission: String
    ) {
    PERSON_READ("person:read"),
    PERSON_WRITE("person:write");
}