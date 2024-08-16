package com.testcase.justvkbot.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Запрос-подтверждение адреса сервера
 * При получении данного запроса сервер должен вернуть vk.confirmation_key
 */
data class ConfirmationRequest(
    @JsonProperty("group_id")
    val groupId: Long
) : Request()
