package com.example.venka.demo.controllers

import com.example.venka.demo.model.Post
import com.example.venka.demo.model.PostHeader
import com.example.venka.demo.repos.PostRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.time.LocalDate
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver

private const val TEMPLATES_DIR = "/templates/"
private const val HTML_ENDING = ".html"

@ExtendWith(MockKExtension::class)
class PostControllerTest {

    private lateinit var mockMvc: MockMvc

    @InjectMockKs
    private lateinit var postController: PostController

    @MockK
    private lateinit var postRepository: PostRepository

    private val postHeader = PostHeader("name", LocalDate.now(), "fullName")
    private val postHeader2 = PostHeader("name2", LocalDate.now(), "fullName2")

    private val post = Post(postHeader, "text")

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController)
                .setViewResolvers(viewResolver())
                .build()
    }

    // for error "circular view path"
    private fun viewResolver(): InternalResourceViewResolver {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix(TEMPLATES_DIR)
        viewResolver.setSuffix(HTML_ENDING)
        return viewResolver
    }

    @Test
    fun testGetFirstPost() {
        every { postRepository.listHeaders() } returns listOf(postHeader, postHeader2)
        every { postRepository[postHeader] } returns post

        val result = mockMvc.perform(get("/post"))
                .andExpect(status().`is`(200))
                .andExpect(model().attribute("post", equalTo(post)))
                .andReturn()

        assertEquals(result.response.forwardedUrl, "${TEMPLATES_DIR}post$HTML_ENDING")
    }
}
