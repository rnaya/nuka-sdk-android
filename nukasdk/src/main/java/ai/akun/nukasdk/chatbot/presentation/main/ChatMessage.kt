package ai.akun.nukasdk.chatbot.presentation.main

data class ChatMessage(
    val text: String?,
    val audioFilePath: String?,
    val intent: String?
)

enum class ChatMessageIntent(val description: String) {
    WELCOME("nuka.welcome")
}