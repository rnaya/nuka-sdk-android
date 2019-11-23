package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.di.component.DaggerActivityComponent
import ai.akun.nukasdk.chatbot.di.module.ActivityModule
import ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter.ChatMessagesAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat_bot.*
import javax.inject.Inject

class ChatBotActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var chatBotViewModel: ChatBotViewModel
    private lateinit var chatMessagesAdapter: ChatMessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)

        injectDependency()
        setUpViewModel()
        setUpToolbar()
        setUpChatMessagesList()
        setUpMessageBar()

        getMessages()
    }

    private fun setUpViewModel() {
        chatBotViewModel = ViewModelProviders.of(this, viewModelFactory)[ChatBotViewModel::class.java]
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
            onBackPressed()
        }
        chatBotViewModel.onLoading().observe(this, Observer {loading ->
            if(loading)
                botStatus.text = getString(R.string.status_writing)
            else
                botStatus.text = getString(R.string.status_online)
        })
    }

    private fun setUpChatMessagesList() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        chatMessages.layoutManager = layoutManager

        chatMessagesAdapter = ChatMessagesAdapter()
        chatMessages.adapter = chatMessagesAdapter

        chatBotViewModel.onChatMessagesUpdated().observe(this, Observer {
            loadMessages(it)
        })
    }

    private fun setUpMessageBar() {
        messageContent.doAfterTextChanged {
            if(messageContent.text.toString().isEmpty()) {
                disableTextMessageSending()
            } else {
                enableTextMessageSending()
            }
        }
        messageContent.setOnEditorActionListener { _, actionId, _ ->
            var action = false
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendTextMessage(messageContent.text.toString())
                action = true
            }

            action
        }
        sendTextMessage.setOnClickListener {
            sendTextMessage(messageContent.text.toString())
        }
        sendAudioMessage.setOnTouchListener { _, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_DOWN){
                audioRecordingAlert.visibility = View.VISIBLE
            }
            if(motionEvent.action == MotionEvent.ACTION_UP){
                audioRecordingAlert.visibility = View.GONE
            }

            true
        }
    }

    private fun getMessages() {
        chatBotViewModel.fetchMessages()
    }

    private fun loadMessages(messages: List<ChatMessage>) {
        chatMessagesAdapter.loadMessages(messages)
        messageContent.setText("")
        scrollToLastMessage()
    }

    private fun disableTextMessageSending() {
        sendTextMessage.alpha = 0.5f
        sendTextMessage.isEnabled = false
    }

    private fun enableTextMessageSending() {
        sendTextMessage.alpha = 1f
        sendTextMessage.isEnabled = true
    }

    private fun sendTextMessage(text: String) {
        chatBotViewModel.sendTextChatMessage(text)
    }

    private fun scrollToLastMessage() {
        chatMessages.scrollToPosition(chatMessagesAdapter.itemCount - 1)
    }
}
