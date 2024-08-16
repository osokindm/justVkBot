package com.testcase.justvkbot.server

import com.testcase.justvkbot.data.ConfirmationRequest
import com.testcase.justvkbot.data.NewMessageRequest
import com.testcase.justvkbot.data.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RequestHandler(private val responder: Responder) {

    @Value("\${vk.confirmation_key}")
    private lateinit var confirmationKey: String
    fun logger(): Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun handleRequest(@RequestBody request: Request): String {
        return when (request) {
            is ConfirmationRequest -> handleConfirmation()
            is NewMessageRequest -> handleNewMessage(request)
            else -> {
                logger().info(request.toString())
                return "io"
            }
        }
    }

    private fun handleNewMessage(request: NewMessageRequest): String {
        logger().error(request.toString())
        responder.sendMessage(request)
        return HttpStatus.OK.reasonPhrase
    }

    private fun handleConfirmation(): String {
        return confirmationKey
    }

}
