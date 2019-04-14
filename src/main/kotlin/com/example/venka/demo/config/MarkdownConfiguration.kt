package com.example.venka.demo.config

import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MarkdownConfiguration {

    @Bean
    fun commonMarkFlavourDescriptor(): CommonMarkFlavourDescriptor = CommonMarkFlavourDescriptor()

    @Bean
    fun markdownParser(flavour: CommonMarkFlavourDescriptor): MarkdownParser = MarkdownParser(flavour)

    @Bean
    fun htmlGeneratorBuilder(flavour: CommonMarkFlavourDescriptor, markdownParser: MarkdownParser):
            (String) -> HtmlGenerator = {
        HtmlGenerator(it, markdownParser.buildMarkdownTreeFromString(it), flavour)
    }
}
