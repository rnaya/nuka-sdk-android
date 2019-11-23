package ai.akun.nukasdk.chatbot.presentation.main

data class ChatMessage(
    val text: String?,
    val audioFilePath: String?,
    val sent: Boolean
)