package com.example.venka.demo.controllers

import com.example.venka.demo.model.Post
import com.example.venka.demo.repos.PostsRepository
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

@Controller
class TestController(val postsRepository: PostsRepository) {

    @GetMapping("/post")
    @ModelAttribute("post")
    fun post(): Post {
        val names = postsRepository.listHeaders()

        return postsRepository[names[0]]
    }
}
