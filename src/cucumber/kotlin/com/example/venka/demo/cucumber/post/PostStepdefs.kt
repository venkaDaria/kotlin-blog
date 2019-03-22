package com.example.venka.demo.cucumber.post

import com.example.venka.demo.model.Post
import com.example.venka.demo.model.PostHeader
import com.example.venka.demo.repos.PostRepository
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class PostStepdefs {

    private lateinit var title: PostHeader
    private lateinit var postExample: Post
    private lateinit var post: Post

    @MockK
    private lateinit var postRepository: PostRepository

    @Given("^post title is \"([^\"]*)\"$")
    fun postTitleIs(title: PostHeader) {
        this.title = title
    }

    @And("^post text is \"([^\"]*)\"$")
    fun postTextIs(text: String) {
        this.postExample = Post(title, text)
    }

    @When("^I ask to give me a post$")
    fun iAskToGiveMeAPost() {
        every { postRepository[title] } returns postExample

        this.post = postRepository[title]
    }

    @Then("^Text must be \"([^\"]*)\"$")
    fun textMustBe(answer: String) {
        assertEquals(post.text, answer)
    }

    @Then("^Name must be \"([^\"]*)\"$")
    fun nameMustBe(answer: String) {
        assertEquals(post.header.name, answer)
    }

    @Then("^Full name must be \"([^\"]*)\"$")
    fun fullNameMustBe(answer: String) {
        assertEquals(post.header.fullName, answer)
    }

    @Then("^Date must be \"([^\"]*)\"$")
    fun dateMustBe(answer: LocalDate) {
        assertEquals(post.header.date, answer)
    }
}
