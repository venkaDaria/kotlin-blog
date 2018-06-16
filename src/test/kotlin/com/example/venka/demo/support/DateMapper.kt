package com.example.venka.demo.support

import io.cucumber.cucumberexpressions.Transformer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateMapper : Transformer<LocalDate> {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun transform(date: String): LocalDate = LocalDate.parse(date, formatter)
}