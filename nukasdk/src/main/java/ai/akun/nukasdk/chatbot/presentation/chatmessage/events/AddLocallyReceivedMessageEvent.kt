package ai.akun.nukasdk.chatbot.presentation.chatmessage.events

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent

data class AddLocallyReceivedMessageEvent(
    val text: String,
    val intent: ChatMessageIntent
)