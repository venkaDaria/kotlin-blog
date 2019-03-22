package com.example.venka.demo.controllers

import com.example.venka.demo.model.Post
import com.example.venka.demo.repos.PostRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(val postRepository: PostRepository) {

    @GetMapping("/post")
    @ModelAttribute("post")
    fun post(): Post {
        val headers = postRepository.listHeaders()

        return postRepository[headers[0]]
    }
}
