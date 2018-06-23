package com.example.venka.demo.cucumber.support

import com.example.venka.demo.model.PostHeader
import com.example.venka.demo.model.toPostHeader
import cucumber.api.Transformer
import java.io.File

class PostHeaderMapper : Transformer<PostHeader>() {

    override fun transform(value: String?): PostHeader = File(value).toPostHeader()
}
