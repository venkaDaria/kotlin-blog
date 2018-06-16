package com.example.venka.demo.support

import com.example.venka.demo.model.Post
import com.example.venka.demo.model.PostHeader
import cucumber.api.TypeRegistry
import cucumber.api.TypeRegistryConfigurer
import io.cucumber.cucumberexpressions.ParameterType
import io.cucumber.cucumberexpressions.Transformer

import java.time.LocalDate
import java.util.Locale

import java.util.Locale.ENGLISH

class TypeRegistryConfiguration : TypeRegistryConfigurer {

    override fun locale(): Locale = ENGLISH

    override fun configureTypeRegistry(typeRegistry: TypeRegistry) {
        typeRegistry.defineParameterType(ParameterType(
                "LocalDate",
                ".*",
                LocalDate::class.java,
                DateMapper()
        ))

        typeRegistry.defineParameterType(ParameterType(
                "PostHeader",
                ".*",
                PostHeader::class.java,
                PostHeaderMapper()
        ))
    }
}