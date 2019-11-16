package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter.ChatMessagesAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat_bot.*

class ChatBotActivity : AppCompatActivity(), ChatBotContract.View {

    private lateinit var presenter: ChatBotContract.Presenter
    private lateinit var chatMessagesAdapter: ChatMessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)
        setUpToolbar()
        setUpChatMessagesList()
        setUpMessageBar()

        presenter = ChatBotPresenter(this) //use dagger
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            presenter.onBackPressed()
        }
    }

    private fun setUpChatMessagesList() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        chatMessages.layoutManager = layoutManager

        chatMessagesAdapter = ChatMessagesAdapter()
        chatMessages.adapter = chatMessagesAdapter
    }

    private fun setUpMessageBar() {
        sendTextMessage.setOnClickListener {
            presenter.onTextMessageSent(messageContent.text.toString())
        }
    }

    override fun loadMessages(messages: List<String>) {
        chatMessagesAdapter.loadMessages(messages)
    }

    override fun addNewMessage(message: String) {
        chatMessagesAdapter.addNewMessage(message)
        messageContent.setText("")
    }

    override fun navigateBack() {
        onBackPressed()
    }
}
