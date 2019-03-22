package com.example.venka.demo.model

import com.example.venka.demo.exception.PostHeaderParsingException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.File
import java.time.LocalDate

class PostHeaderTest {

    private val postHeader = PostHeader("name", LocalDate.now(), "fullName")
    private val postHeader2 = PostHeader("name", LocalDate.now(), "fullName")
    private val postHeader3 = PostHeader("name2", LocalDate.now(), "fullName2")

    @Test
    fun testEquals() {
        assertEquals(postHeader, postHeader2)
    }

    @Test
    fun testEquals_False() {
        assertNotEquals(postHeader, postHeader3)
    }

    @Test
    fun toPostHeader_Exception() {
        val exception = assertThrows<PostHeaderParsingException> ("Post Header cannot be parsed") {
            File("hello").toPostHeader()
        }
        assertEquals("Post Header cannot be parsed", exception.message)
    }
}
