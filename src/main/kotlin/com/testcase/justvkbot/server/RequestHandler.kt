package com.testcase.justvkbot.server

import com.testcase.justvkbot.data.ConfirmationRequest
import com.testcase.justvkbot.data.NewMessageRequest
import com.testcase.justvkbot.data.OtherRequest
import com.testcase.justvkbot.data.Request
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RequestHandler(
    private val responder: Responder,
    private val logger: Logger
) {

    @Value("\${vk.confirmation_key}")
    private lateinit var confirmationKey: String

    @Value("\${vk.secret_key}")
    private lateinit var secretKey: String

    @Value("\${vk.group_id}")
    private var groupId: Int = 0

    @PostMapping
    fun handleRequest(@RequestBody request: Request): ResponseEntity<String> {
        return when (request) {
            is ConfirmationRequest -> handleConfirmation()
            is NewMessageRequest -> handleNewMessage(request)
            is OtherRequest -> handleOtherRequests(request)
        }
    }

    private fun handleNewMessage(request: NewMessageRequest): ResponseEntity<String> {
        logger.error(request.toString())
        if (request.secret != secretKey || request.groupId != groupId) {
            logger.warn("Invalid secret or group ID")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request")
        }
        responder.sendMessage(request)
        return ResponseEntity.ok(HttpStatus.OK.reasonPhrase)
    }

    private fun handleConfirmation(): ResponseEntity<String> {
        logger.info("Handling confirmation request")
        return ResponseEntity.ok(confirmationKey)
    }

    private fun handleOtherRequests(request: OtherRequest): ResponseEntity<String> {
        logger.info("Received not allowed request: $request")
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Only message_new requests are allowed")
    }

}
