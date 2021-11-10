package com.artem.springsecurity.controller

import com.artem.springsecurity.model.Person
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController {

    private val persons = mutableListOf(
        Person(
            id = 1,
            name = "Mike",
            age = 19
        ),
        Person(
            id = 2,
            name = "Fred",
            age = 22
        )
    )

    @GetMapping
    fun getAll(): List<Person> = persons

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): Person =
        persons.first { it.id == id }

    @PostMapping
    fun create(@RequestBody person: Person) = "SUCCESSFUL".also { persons.add(person) }
}