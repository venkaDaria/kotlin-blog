package com.example.venka.demo.repos

import com.example.venka.demo.model.Post
import com.example.venka.demo.model.PostHeader
import com.example.venka.demo.model.toPost
import com.example.venka.demo.model.toPostHeader
import org.intellij.markdown.html.HtmlGenerator
import java.io.IOException
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Repository

private const val ARTICLES_PATH = "/static/posts/"

private val articlesDir = ClassPathResource(ARTICLES_PATH).file

@Repository
class PostRepository(
        private val htmlGeneratorBuilder: (String) -> HtmlGenerator
) {

    @Throws(IOException::class)
    fun listHeaders(): List<PostHeader> = articlesDir.listFiles().map {
        it.toPostHeader()
    }

    @Throws(IOException::class)
    operator fun get(postHeader: PostHeader): Post {
        val markdownFile = ClassPathResource(ARTICLES_PATH + postHeader.fullName).file

        val src = markdownFile.readText(Charsets.UTF_8)
        val html = htmlGeneratorBuilder(src).generateHtml()

        return postHeader.toPost(html)
    }
}
