package com.example.venka.demo.cucumber.postHeader

import com.example.venka.demo.exception.PostHeaderParsingException
import com.example.venka.demo.model.PostHeader
import com.example.venka.demo.model.toPostHeader
import com.example.venka.demo.support.ExceptionHandler
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.testng.Assert.assertEquals
import java.io.File
import java.io.FileNotFoundException
import java.time.LocalDate

class PostHeaderStepdefs {

    private lateinit var title: String
    private lateinit var postHeader: PostHeader

    private val exceptionHandler = ExceptionHandler()

    @Given("^title is \"([^\"]*)\"$")
    fun titleIs(title: String) {
        this.title = title
    }

    @When("I ask to give me a PostHeader")
    fun iAskToGiveMeAPostHeader() {
        try {
            this.postHeader = File(title).toPostHeader()
        } catch (ex: PostHeaderParsingException) {
            exceptionHandler.add(ex)
        }
    }

    @Then("^Name must be \"([^\"]*)\"$")
    fun nameMustBe(answer: String) {
        assertEquals(this.postHeader.name, answer)
    }

    @Then("^Full name must be \"([^\"]*)\"$")
    fun fullNameMustBe(answer: String) {
        assertEquals(this.postHeader.fullName, answer)
    }

    @Then("^Date must be \"([^\"]*)\"$")
    fun dateMustBe(answer: LocalDate) {
        assertEquals(this.postHeader.date, answer)
    }

    @And("^Parsing is fail$")
    fun parsingIsFail() {
        val exceptions = exceptionHandler.popAll()

        assertThat(exceptions, hasSize(1))
        assertEquals(exceptions[0]::class, PostHeaderParsingException::class)
    }

    @And("^File isn't found$")
    fun fileIsnTFound() {
        val exceptions = exceptionHandler.popAll()

        assertThat(exceptions, hasSize(1))
        assertEquals(exceptions[0]::class, FileNotFoundException::class)
    }

    @Then("^a failure is expected$")
    fun thenAFailureIsExpected() {
        exceptionHandler.expectException()
    }
}
