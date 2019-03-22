package com.example.venka.demo.model

import com.example.venka.demo.exception.PostHeaderParsingException
import java.io.File
import java.io.FileNotFoundException
import java.time.LocalDate

data class PostHeader(val name: String, val date: LocalDate, val fullName: String)

data class Post(val header: PostHeader, val text: String)

fun PostHeader.toPost(text: String): Post = Post(this, text)

private const val NAME_PARTS_SIZE: Int = 4

fun File.toPostHeader(): PostHeader {
    val parts = name.removeSuffix(".md").split("-")

    if (parts.size != NAME_PARTS_SIZE) {
        throw PostHeaderParsingException("Post Header cannot be parsed")
    }

    return PostHeader(parts[0], parts.drop(0).toDate(), name)
}

@Suppress("MagicNumber")
private fun List<String>.toDate(): LocalDate {
    val year = this[3].toInt()
    val month = this[2].toInt()
    val dayOfMonth = this[1].toInt()

    return LocalDate.of(year, month, dayOfMonth)
}
