package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.di.component.DaggerActivityComponent
import ai.akun.nukasdk.chatbot.di.module.ActivityModule
import ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter.ChatMessagesAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat_bot.*
import javax.inject.Inject

class ChatBotActivity : AppCompatActivity(), ChatBotContract.View {

    @Inject
    lateinit var presenter: ChatBotContract.Presenter

    private lateinit var chatMessagesAdapter: ChatMessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)
        setUpToolbar()
        setUpChatMessagesList()
        setUpMessageBar()

        injectDependency()
        presenter.attach(this)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
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
        messageContent.doAfterTextChanged {
            presenter.onTextMessageDraftUpdated(messageContent.text.toString())
        }
        messageContent.setOnEditorActionListener { _, actionId, _ ->
            var action = false
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                presenter.onTextMessageSent(messageContent.text.toString())
                action = true
            }

            action
        }
        sendTextMessage.setOnClickListener {
            presenter.onTextMessageSent(messageContent.text.toString())
        }
    }

    override fun loadMessages(messages: List<String>) {
        chatMessagesAdapter.loadMessages(messages)
        scrollToLastMessage()
    }

    override fun disableTextMessageSending() {
        sendTextMessage.alpha = 0.5f
        sendTextMessage.isEnabled = false
    }

    override fun enableTextMessageSending() {
        sendTextMessage.alpha = 1f
        sendTextMessage.isEnabled = true
    }

    override fun addNewMessage(message: String) {
        chatMessagesAdapter.addNewMessage(message)
        messageContent.setText("")
        scrollToLastMessage()
    }

    private fun scrollToLastMessage() {
        chatMessages.scrollToPosition(chatMessagesAdapter.itemCount - 1)
    }

    override fun navigateBack() {
        onBackPressed()
    }
}
