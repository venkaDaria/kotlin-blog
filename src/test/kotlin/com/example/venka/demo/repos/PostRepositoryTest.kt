package com.example.venka.demo.repos

import com.example.venka.demo.model.PostHeader
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.FileNotFoundException
import java.time.LocalDate

@SpringBootTest
class PostRepositoryTest {

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

    @Test
    fun testGet_NotFound() {
        val exception = assertThrows<FileNotFoundException> ("File not found") {
            postRepository[postHeader2]
        }

        assertEquals(
                "class path resource [static/posts/fullName] cannot be resolved to URL because it does not exist",
                exception.message
        )
    }
}
