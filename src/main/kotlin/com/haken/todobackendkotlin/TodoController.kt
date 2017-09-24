package com.haken.todobackendkotlin

import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong

data class Todo(var id: Int = 0, var title: String = "", var completed: Boolean = false, var order: Int = -1) {
    @Suppress("unused")
    constructor() : this(0)

    @Suppress("unused")
    val url: String
        get() = "${Config.root}/$id"
}

@RestController
@CrossOrigin
class TodoController {

    val counter = AtomicInteger()
    val todos = HashMap<Int, Todo>()

    @GetMapping("/")
    fun getAll() = todos.values.toList()

    @PostMapping("/")
    fun createTodo(@RequestBody todo: Todo): Todo {
        val id = counter.incrementAndGet()
        val createdTodo: Todo = todo.copy(id = id)
        todos[id] = createdTodo
        return createdTodo
    }

    @DeleteMapping("/")
    fun clean() = todos.clear()

    @GetMapping("/{id}")
    fun getTodo(@PathVariable("id") id: Int) = todos[id]

    @DeleteMapping("/{id}")
    fun remove(@PathVariable("id") id: Int) = todos.remove(id)

    @PatchMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody todo: Todo): Todo? {
        if (!todos.contains(id)) return null

        val old = todos[id]!!
        if (!todo.title.isEmpty()) old.title = todo.title
        if (todo.completed) old.completed = true
        if (todo.order > -1) old.order = todo.order

        return old
    }
}
