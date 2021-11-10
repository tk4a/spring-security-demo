package com.artem.springsecurity.controller

import com.artem.springsecurity.model.Person
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController {

    @GetMapping
    fun getAll(): List<Person> = listOf()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): Person =
        Person(
            id = 1,
            name = "Mike",
            age = 19
        )
}