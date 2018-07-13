package com.example.venka.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

// Controller advice ?
// frontend work when load post
// dup code remove
// gradle commands + task for each pair glue-package and union this tasks
// deps level(compile...)