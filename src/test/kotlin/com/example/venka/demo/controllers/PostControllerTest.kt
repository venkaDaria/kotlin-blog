package com.example.venka.demo.controllers

import com.example.venka.demo.model.Post
import com.example.venka.demo.model.PostHeader
import com.example.venka.demo.repos.PostRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.hamcrest.Matchers.equalTo
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.time.LocalDate
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.testng.Assert.assertEquals

private const val TEMPLATES_DIR = "/templates/"
private const val HTML_ENDING = ".html"

class PostControllerTest {

    private lateinit var mockMvc: MockMvc

    @InjectMockKs
    private lateinit var postController: PostController

    @MockK
    private lateinit var postRepository: PostRepository

    private val postHeader = PostHeader("name", LocalDate.now(), "fullName")
    private val postHeader2 = PostHeader("name2", LocalDate.now(), "fullName2")

    private val post = Post(postHeader, "text")

    @BeforeClass
    fun setUp() {
        MockKAnnotations.init(this)

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
