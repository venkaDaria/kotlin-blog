package com.example.venka.demo.model

import com.example.venka.demo.exception.PostHeaderParsingException
import org.testng.Assert.assertEquals
import org.testng.Assert.assertNotEquals
import org.testng.annotations.Test
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

    @Test(expectedExceptions = [PostHeaderParsingException::class])
    fun toPostHeader_Exception() {
        File("hello").toPostHeader()
    }
}
