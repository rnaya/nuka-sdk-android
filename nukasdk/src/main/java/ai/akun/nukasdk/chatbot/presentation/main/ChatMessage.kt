package ai.akun.nukasdk.chatbot.presentation.main

data class ChatMessage(
    val text: String? = null,
    val audioFilePath: String? = null,
    val intent: String? = null,
    val imageUri: String? = null
)

enum class ChatMessageIntent(val description: String) {
    WELCOME("nuka.welcome"),
    NEXT_MATCHES("nuka.matches.next"),
    RANKING("nuka.ranking"),
    PLAYERS_LIST("nuka.players.list"),
    FAN_SHOP("nuka.buy.fanshop"),
    ARTICLES("nuka.articles"),
    GIF("nuka.botactions.giphy")
}