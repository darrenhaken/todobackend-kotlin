package com.haken.todobackendkotlin

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

object Config {
    var root: String = "http://localhost:8080"
}

fun main(args: Array<String>) {
    if (args.isNotEmpty()) Config.root=args[0]

    SpringApplication.run(Application::class.java, *args)
}
