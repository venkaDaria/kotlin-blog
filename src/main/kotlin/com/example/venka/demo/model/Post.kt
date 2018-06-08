package com.example.venka.demo.model

import com.example.venka.demo.exception.PostHeaderParsingException
import java.io.File
import java.time.LocalDate

class PostHeader(val name: String, val date: LocalDate, val fullName: String)

class Post(val header: PostHeader, val text: String)

fun PostHeader.toPost(text: String): Post = Post(this, text)

fun File.toPostHeader(): PostHeader {
    val parts = name.removeSuffix(".md").split("-")

    if (parts.size != 4) {
        throw PostHeaderParsingException()
    }

    return PostHeader(parts[0], parts.drop(0).toDate(), name)
}

private fun List<String>.toDate(): LocalDate {
    val year = this[3].toInt()
    val month = this[2].toInt()
    val dayOfMonth = this[1].toInt()

    return LocalDate.of(year, month, dayOfMonth)
}
