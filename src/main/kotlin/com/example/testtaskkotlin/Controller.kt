package com.example.testtaskkotlin

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import kotlin.random.Random


@RestController
class Controller {

    @Value("\${vk.api.token}")
    private lateinit var apiToken: String

    @Value("\${vk.confirmation.token}")
    private lateinit var confirmationToken: String

    @PostMapping("/callback")
    fun callback(@RequestBody vkMessage: VkMessage): ResponseEntity<String> {
        try {
            return when (vkMessage.type) {
                "confirmation" -> ResponseEntity.ok(confirmationToken)
                "message_new" -> {
                    val messageText = vkMessage.obj?.message?.text ?: return ResponseEntity.badRequest().body("Message text is missing")
                    val userId = vkMessage.obj?.message?.from_id ?: return ResponseEntity.badRequest().body("User ID is missing")
                    sendMessage(userId, messageText)
                    ResponseEntity.ok("ok")
                }
                else -> ResponseEntity.ok("Unsupported event")
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.badRequest().body("Something wrong")
        }
    }

    private fun sendMessage(userId: Int, text: String) {
        try {
            val url = "https://api.vk.com/method/messages.send"
            val randomId = Random.nextInt(Int.MAX_VALUE)

            val uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("user_id", userId)
                    .queryParam("message", "You said: "+text)
                    .queryParam("random_id", randomId)
                    .queryParam("access_token", apiToken)
                    .queryParam("v", "5.199")
                    .build()

            val restTemplate = RestTemplate()

            val uri = uriBuilder.toUri()
            val response = restTemplate.getForObject(uri, String::class.java)
            println(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
