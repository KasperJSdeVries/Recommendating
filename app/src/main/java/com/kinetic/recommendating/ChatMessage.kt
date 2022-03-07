package com.kinetic.recommendating

import java.util.*

class ChatMessage(message: String, messageSender: String, messageReceiver: String) {
    private val messageText = message
    private val messageSender = messageSender
    private val messageReceiver = messageReceiver
    private val messageTime = Date().time
}
