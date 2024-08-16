package com.testcase.justvkbot.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class BotConfig {

    @Bean
    fun logger(): Logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun webClient(): WebClient = WebClient.create("https://api.vk.com/method/")

}
