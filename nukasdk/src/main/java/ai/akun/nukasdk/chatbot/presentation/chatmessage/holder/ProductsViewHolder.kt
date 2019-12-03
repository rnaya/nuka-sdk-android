package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter.ProductsAdapter
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_row_scrollable_chat_message.view.*

class ProductsViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        val adapter = ProductsAdapter()
        itemView.scrollableList.layoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        itemView.scrollableList.adapter = adapter
        adapter.load(chatMessage.webhookPayload?.products!!)
    }

}