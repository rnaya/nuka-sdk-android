package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_message_sent.view.*

class ChatMessagesAdapter : RecyclerView.Adapter<ChatMessagesAdapter.MessageHolder>() {

    private var messages: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val inflatedView = parent.inflate(R.layout.item_row_message_sent, false)
        return MessageHolder(inflatedView)
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    fun loadMessages(messages: List<String>) {
        this.messages = messages.toMutableList()
        notifyDataSetChanged()
    }

    fun addNewMessage(message: String) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var message: String

        fun bind(message: String) {
            this.message = message
            itemView.content.text = message
        }

    }

}