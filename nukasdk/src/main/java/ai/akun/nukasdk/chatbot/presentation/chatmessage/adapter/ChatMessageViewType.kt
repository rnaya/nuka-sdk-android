package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.AudioChatMessageSentHolder
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.ChatMessageHolder
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.ChatMessageReceivedHolder
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.TextChatMessageSentHolder
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.view.ViewGroup

class ChatMessageView {

    companion object {
        fun getChatMessageViewType(chatMessage: ChatMessage) : Int {
            return if(chatMessage.sent && chatMessage.text?.isNotEmpty() == true)
                Type.TEXT_SENT.id
            else if (chatMessage.sent && chatMessage.audioFilePath?.isNotEmpty() == true)
                Type.AUDIO_SENT.id
            else
                Type.RECEIVED.id
        }

        fun getChatMessageViewHolder(parent: ViewGroup, chatMessageViewType: Int): ChatMessageHolder {
            return when(chatMessageViewType) {
                Type.TEXT_SENT.id -> {
                    val inflatedView = parent.inflate(Type.TEXT_SENT.layoutId, false)
                    TextChatMessageSentHolder(inflatedView)
                }
                Type.AUDIO_SENT.id -> {
                    val inflatedView = parent.inflate(Type.AUDIO_SENT.layoutId, false)
                    AudioChatMessageSentHolder(inflatedView)
                }
                Type.RECEIVED.id -> {
                    val inflatedView = parent.inflate(Type.RECEIVED.layoutId, false)
                    ChatMessageReceivedHolder(inflatedView)
                }
                else -> {
                    val inflatedView = parent.inflate(Type.TEXT_SENT.layoutId, false)
                    TextChatMessageSentHolder(inflatedView)
                }
            }
        }
    }

    enum class Type(val id: Int, val layoutId: Int) {
        TEXT_SENT(1, R.layout.item_row_text_chat_message_sent),
        AUDIO_SENT(2, R.layout.item_row_audio_chat_message_sent),
        RECEIVED(3, R.layout.item_row_chat_message_received)
    }

}

