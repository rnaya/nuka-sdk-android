package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.*
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.view.ViewGroup

data class ChatMessage(
    val text: String? = null,
    val audioFilePath: String? = null,
    val intent: ChatMessageIntent,
    val data: List<Card>? = null
)

data class Card(
    val title: String,
    val subtitle: String,
    val imageUri: String,
    val buttons: List<Button>?
)

data class Button(
    val text: String,
    val postback: String
)

enum class ChatMessageIntent(val displayName: String) : ViewHolderSource {
    SENT_TEXT("nuka.sent.text") {
        override fun getViewType() = 1

        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_sent_text_chat_message, false)
            return SentTextChatMessageViewHolder(inflatedView)
        }
    },
    SENT_AUDIO("nuka.sent.audio"){
        override fun getViewType() = 2

        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_sent_audio_chat_message, false)
            return SentAudioChatMessageViewHolder(inflatedView)
        }
    },
    RECEIVED_WELCOME("nuka.welcome"){
        override fun getViewType() = 3

        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_received_welcome_text_chat_message, false)
            val viewHolder = ReceivedWelcomeTextChatMessageViewHolder(inflatedView)
            viewHolder.setViewModel(chatBotViewModel!!)
            return viewHolder
        }
    },
    RECEIVED_TEXT("nuka.text"){
        override fun getViewType() = 4

        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_received_text_chat_message, false)
            return ReceivedTextChatMessageViewHolder(inflatedView)
        }
    },
//    RECEIVED_NEXT_MATCHES("nuka.matches.next"){
//        override fun getViewType() = 5
//
//        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
//        }
//    },
//    RECEIVED_RANKING("nuka.ranking"){
//        override fun getViewType() = 6
//
//        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
//        }
//    },
//    RECEIVED_PLAYERS_LIST("nuka.players.list"){
//        override fun getViewType() = 7
//
//        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
//        }
//    },
//    RECEIVED_FAN_SHOP("nuka.buy.fanshop"){
//        override fun getViewType() = 8
//
//        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
//        }
//    },
//    RECEIVED_ARTICLES("nuka.articles"){
//        override fun getViewType() = 9
//
//        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
//        }
//    },
    RECEIVED_GIF("nuka.botactions.giphy"){
        override fun getViewType() = 10

        override fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel?): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_received_gif_chat_message, false)
            return ReceivedGifChatMessageViewHolder(inflatedView)
        }
    },
}

interface ViewHolderSource {
    fun getViewType(): Int
    fun getViewHolder(parent: ViewGroup, chatBotViewModel: ChatBotViewModel? = null) : ChatMessageViewHolder
}