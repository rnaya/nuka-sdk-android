package ai.akun.nukasdk.chatbot.presentation.main

data class ChatMessage(
    val text: String?,
    val audioFilePath: String?,
    val intent: String?
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