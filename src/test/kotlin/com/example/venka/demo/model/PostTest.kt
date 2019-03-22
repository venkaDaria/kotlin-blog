package com.example.venka.demo.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class PostTest {

    private val postHeader = PostHeader("name", LocalDate.now(), "fullName")
    private val postHeader2 = PostHeader("name", LocalDate.now(), "fullName")
    private val postHeader3 = PostHeader("name2", LocalDate.now(), "fullName2")

    private val post = Post(postHeader, "text")
    private val post2 = Post(postHeader2, "text")
    private val post3 = Post(postHeader3, "text")
    private val post4 = Post(postHeader, "text2")

    @Test
    fun testEquals() {
        assertEquals(post, post2)
    }

    @Test
    fun testEquals_Header_False() {
        assertNotEquals(post, post3)
    }

    @Test
    fun testEquals_False() {
        assertNotEquals(post, post4)
    }
}
