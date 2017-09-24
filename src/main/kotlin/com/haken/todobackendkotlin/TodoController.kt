package com.haken.todobackendkotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class TodoController {

    val counter = AtomicLong()

    @GetMapping("/todo")
    fun getTodo() = Todo(counter.incrementAndGet(), "Hello")

}
