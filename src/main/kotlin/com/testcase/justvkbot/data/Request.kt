package com.testcase.justvkbot.data

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    defaultImpl = OtherRequest::class
)
@JsonSubTypes(
    JsonSubTypes.Type(value = ConfirmationRequest::class, name = "confirmation"),
    JsonSubTypes.Type(value = NewMessageRequest::class, name = "message_new")
)
sealed class Request
