package ai.akun.nukasdk.chatbot.data.chatmessage.entities

data class CardEntity (
    val title: String,
    val subtitle: String,
    val imageUri: String,
    val buttons: List<ButtonEntity>?
)

data class ButtonEntity (
    val text: String,
    val postback: String?
)