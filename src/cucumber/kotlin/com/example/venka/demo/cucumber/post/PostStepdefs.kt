package com.example.venka.demo.cucumber.post

import com.example.venka.demo.cucumber.support.DateMapper
import com.example.venka.demo.cucumber.support.PostHeaderMapper
import com.example.venka.demo.model.Post
import com.example.venka.demo.model.PostHeader
import com.example.venka.demo.repos.PostRepository
import cucumber.api.Transform
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.testng.Assert
import java.time.LocalDate

class PostStepdefs {

    private lateinit var title: PostHeader
    private lateinit var postExample: Post
    private lateinit var post: Post

    @MockK
    private lateinit var postRepository: PostRepository

    init {
        MockKAnnotations.init(this)
    }

    @Given("^post title is \"([^\"]*)\"$")
    fun postTitleIs(@Transform(PostHeaderMapper::class) title: PostHeader) {
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
        Assert.assertEquals(post.text, answer)
    }

    @Then("^Name must be \"([^\"]*)\"$")
    fun nameMustBe(answer: String) {
        Assert.assertEquals(post.header.name, answer)
    }

    @Then("^Full name must be \"([^\"]*)\"$")
    fun fullNameMustBe(answer: String) {
        Assert.assertEquals(post.header.fullName, answer)
    }

    @Then("^Date must be \"([^\"]*)\"$")
    fun dateMustBe(@Transform(DateMapper::class) answer: LocalDate) {
        Assert.assertEquals(post.header.date, answer)
    }
}
