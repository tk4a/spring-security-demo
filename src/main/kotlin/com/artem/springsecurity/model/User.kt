package com.artem.springsecurity.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users_")
data class User(
    @Id
    @Column(name = "user_id")
    val id: UUID = UUID.randomUUID(),
    @Column(name = "full_name")
    val fullName: String,
    val email: String,
    val login: String,
    val password: String,
    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    val role: Role,
    @Column(name = "user_status")
    @Enumerated(value = EnumType.STRING)
    val status: Status
) {
    constructor() : this(
        id = UUID.randomUUID(),
        fullName = "",
        email = "",
        login = "",
        password = "",
        role = Role.USER,
        status = Status.ACTIVE
    )
}