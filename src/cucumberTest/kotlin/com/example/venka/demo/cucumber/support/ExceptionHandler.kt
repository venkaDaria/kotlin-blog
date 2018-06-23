package com.example.venka.demo.cucumber.support

import java.util.ArrayList

class ExceptionHandler {
    private var expectException: Boolean = false
    private var exceptions = ArrayList<RuntimeException>()

    fun expectException() {
        expectException = true
    }

    fun add(e: RuntimeException) {
        if (!expectException) {
            throw e
        }

        exceptions.add(e)
        expectException = false
    }

    fun popAll(): List<RuntimeException> {
        val ex = exceptions
        exceptions = ArrayList()
        return ex
    }
}