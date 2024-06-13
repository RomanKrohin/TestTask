package com.example.testtaskkotlin

import com.fasterxml.jackson.annotation.JsonProperty

data class VkMessage(
        val type: String,
        val group_id: Int,
        @JsonProperty("object") val obj: VkMessageObject?
)

data class VkMessageObject(
        val message: VkMessageObjectMessage?
)

data class VkMessageObjectMessage(
        val text: String?,
        val from_id: Int?
)