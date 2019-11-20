package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessage
import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessageAction
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TableLayout
import kotlinx.android.synthetic.main.item_row_message_received.view.*
import kotlinx.android.synthetic.main.item_row_message_sent.view.content

class ReceivedChatMessageHolder(itemView: View) : MessageHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        itemView.content.text = chatMessage.content

        chatMessage.actions?.let {
            addActions(it)
        }
    }

    private fun addActions(actions: List<ChatMessageAction>) {
        actions.forEach { action ->
            val actionButton = Button(itemView.context) //TODO use ChatMessage.Action style resource
            actionButton.text = action.description
            actionButton.layoutParams = TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            itemView.actionsContainer.addView(actionButton)
        }
    }

}