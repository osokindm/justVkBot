package com.testcase.justvkbot.server

import com.testcase.justvkbot.data.NewMessageRequest
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class Responder(
    private val webClient: WebClient,
    private val logger: Logger
) {

    @Value("\${vk.access_key}")
    private lateinit var accessToken: String

    fun sendMessage(request: NewMessageRequest) {
        val message = "Вы сказали: ${request.messageObject.message.text}"
        val randomId = 0

        webClient
            .post()
            .uri { uriBuilder ->
                uriBuilder.path("messages.send")
                    .queryParam("peer_id", request.messageObject.message.peerId)
                    .queryParam("random_id", randomId)
                    .queryParam("message", message)
                    .queryParam("group_id", request.groupId)
                    .queryParam("access_token", accessToken)
                    .queryParam("v", request.v)
                    .build()
            }
            .retrieve()
            .bodyToMono(String::class.java)
            .doOnError { error -> logger.error("Error occurred: ${error.message}") }
            .subscribe { response -> logger.info("Response: $response") }
    }
}