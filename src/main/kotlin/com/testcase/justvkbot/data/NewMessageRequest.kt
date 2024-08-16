package com.testcase.justvkbot.data

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Когда происходит событие, вы получаете данные в JSON, имеющем следующую структуру:
 * @property messageObject - сообщение, инициировавшее событие
 * @property groupId - ID сообщества, в котором произошло событие
 *
 */
data class NewMessageRequest(
    @JsonProperty("group_id")
    val groupId: Int,
    val v: Double,
    val secret: String,
    @JsonProperty("object")
    val messageObject: NewMessageObjectWrapper
) : Request()

data class NewMessageObjectWrapper(
    @JsonProperty("message")
    val message: NewMessageObject
)
/**
 * Объект, описывающий личное сообщение, содержит следующие поля:
 * @property id - идентификатор сообщения
 * @property fromId - идентификатор отправителя
 * @property text - текст сообщения
 */
data class NewMessageObject(
    val id: Int,
    @JsonProperty("peer_id")
    val peerId: Int,
    @JsonProperty("from_id")
    val fromId: Int,
    val text: String
)