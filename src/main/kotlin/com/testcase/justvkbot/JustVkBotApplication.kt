package com.testcase.justvkbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@PropertySource("classpath:local.properties")
class JustVkBotApplication

fun main(args: Array<String>) {
    runApplication<JustVkBotApplication>(*args)
}
