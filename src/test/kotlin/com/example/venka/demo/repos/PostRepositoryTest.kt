package com.example.venka.demo.repos

import com.example.venka.demo.model.PostHeader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.testng.Assert.assertEquals
import org.testng.Assert.assertTrue
import org.testng.annotations.Test
import java.io.FileNotFoundException
import java.time.LocalDate

@SpringBootTest
class PostRepositoryTest : AbstractTestNGSpringContextTests() {

    @Autowired
    private lateinit var postRepository: PostRepository

    private val postHeader = PostHeader("HelloWorld", LocalDate.of(2018, 6, 7),
            "HelloWorld-07-06-2018.md")
    private val postHeader2 = PostHeader("name", LocalDate.now(), "fullName")

    @Test
    fun testListHeaders() {
        val headers = postRepository.listHeaders()

        assertTrue(headers.isNotEmpty())
        assertTrue(headers.contains(postHeader))
    }

    @Test
    fun testGet() {
        val post = postRepository[postHeader]

        assertEquals(post.header, postHeader)
        assertTrue(post.text.isNotBlank())
    }

    @Test(expectedExceptions = [FileNotFoundException::class])
    fun testGet_NotFound() {
        postRepository[postHeader2]
    }
}

private fun String.addHtmlTag(): String {
    return "<!DOCTYPE html><html><head></head>$this</html>"
}
