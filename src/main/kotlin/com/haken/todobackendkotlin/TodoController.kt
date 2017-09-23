package com.haken.todobackendkotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class TodoController {

    val counter = AtomicLong()

    @GetMapping("/greeting")
    fun greeting() = Todo(counter.incrementAndGet(), "Hello")

}
