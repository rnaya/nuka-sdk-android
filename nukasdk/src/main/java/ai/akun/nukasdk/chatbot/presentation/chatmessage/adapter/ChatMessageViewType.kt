package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.*
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.view.ViewGroup

class ChatMessageView {

    companion object {
        fun getChatMessageViewType(chatMessage: ChatMessage) : Int {
            return if(chatMessage.intent.isNullOrBlank() && chatMessage.text?.isNotEmpty() == true)
                Type.TEXT_SENT.id
            else if (chatMessage.intent.isNullOrBlank() && chatMessage.audioFilePath?.isNotEmpty() == true)
                Type.AUDIO_SENT.id
            else if(chatMessage.intent?.isNotEmpty() == true && chatMessage.intent == ChatMessageIntent.WELCOME.description)
                Type.TEXT_RECEIVED_WELCOME.id
            else
                Type.TEXT_RECEIVED.id
        }

        fun getChatMessageViewHolder(parent: ViewGroup, chatMessageViewType: Int): ChatMessageHolder {
            return when(chatMessageViewType) {
                Type.TEXT_SENT.id -> {
                    val inflatedView = parent.inflate(Type.TEXT_SENT.layoutId, false)
                    SentTextChatMessageHolder(inflatedView)
                }
                Type.AUDIO_SENT.id -> {
                    val inflatedView = parent.inflate(Type.AUDIO_SENT.layoutId, false)
                    SentAudioChatMessageHolder(inflatedView)
                }
                Type.TEXT_RECEIVED_WELCOME.id -> {
                    val inflatedView = parent.inflate(Type.TEXT_RECEIVED_WELCOME.layoutId, false)
                    ReceivedWelcomeTextChatMessageHolder(inflatedView)
                }
                Type.TEXT_RECEIVED.id -> {
                    val inflatedView = parent.inflate(Type.TEXT_RECEIVED.layoutId, false)
                    ReceivedTextChatMessageHolder(inflatedView)
                }
                else -> {
                    val inflatedView = parent.inflate(Type.TEXT_SENT.layoutId, false)
                    SentTextChatMessageHolder(inflatedView)
                }
            }
        }
    }

    enum class Type(val id: Int, val layoutId: Int) {
        TEXT_SENT(1, R.layout.item_row_sent_text_chat_message),
        AUDIO_SENT(2, R.layout.item_row_sent_audio_chat_message),
        TEXT_RECEIVED(3, R.layout.item_row_received_text_chat_message),
        TEXT_RECEIVED_WELCOME(4, R.layout.item_row_welcome_text_chat_message)
    }

}

