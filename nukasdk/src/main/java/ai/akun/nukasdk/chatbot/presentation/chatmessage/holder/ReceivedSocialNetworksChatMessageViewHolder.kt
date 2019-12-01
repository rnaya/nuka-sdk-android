package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter.SocialNetworksAdapter
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_row_received_social_networks_chat_message.view.*
import kotlinx.android.synthetic.main.item_row_received_text_chat_message.view.*

class ReceivedSocialNetworksChatMessageViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    override fun bind(chatMessage: ChatMessage, onSendNewMessage: ((String) -> Unit)?) {
        if(!chatMessage.text.isNullOrBlank()) {
            itemView.socialNetworks.visibility = View.GONE
            itemView.messageContainer.visibility = View.VISIBLE
            itemView.content.text = chatMessage.text
        } else {
            val adapter = SocialNetworksAdapter()
            itemView.socialNetworks.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.socialNetworks.adapter = adapter
            adapter.load(chatMessage.data!!)
        }
    }

}