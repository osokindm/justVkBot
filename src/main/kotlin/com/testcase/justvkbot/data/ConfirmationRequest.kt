package com.testcase.justvkbot.data

import com.fasterxml.jackson.annotation.JsonProperty


data class ConfirmationRequest(
    @JsonProperty("group_id")
    val groupId: Long
) : Request()
