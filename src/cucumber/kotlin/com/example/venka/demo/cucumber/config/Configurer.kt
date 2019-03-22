package com.example.venka.demo.cucumber.config

import com.example.venka.demo.model.PostHeader
import com.example.venka.demo.model.toPostHeader
import cucumber.api.TypeRegistry
import cucumber.api.TypeRegistryConfigurer
import io.cucumber.cucumberexpressions.ParameterType
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import java.util.Locale

class Configurer : TypeRegistryConfigurer {

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun configureTypeRegistry(registry: TypeRegistry) {
        registry.defineParameterType(ParameterType("postHeader", ".*?", PostHeader::class.java) {
            value: String -> File(value).toPostHeader()
        })

        registry.defineParameterType(ParameterType("date", ".*?", LocalDate::class.java) {
            value: String -> LocalDate.parse(value, formatter)
        })
    }

    override fun locale(): Locale = Locale.ENGLISH
}