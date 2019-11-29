package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.di.component.DaggerActivityComponent
import ai.akun.nukasdk.chatbot.di.module.ActivityModule
import ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter.ChatMessagesAdapter
import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat_bot.*
import java.util.*
import javax.inject.Inject


class ChatBotActivity : AppCompatActivity() {

    companion object {
        const val RECORD_AUDIO_PERMISSION_REQUEST_CODE = 1
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var chatBotViewModel: ChatBotViewModel
    private lateinit var chatMessagesAdapter: ChatMessagesAdapter

    private var audioRecorder: MediaRecorder = MediaRecorder()
    private var outputAudioFilePath = ""

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
                botStatus.text = getString(R.string.chatbot_is_writing)
            else
                botStatus.text = getString(R.string.online)
        })
    }

    private fun setUpChatMessagesList() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        chatMessages.layoutManager = layoutManager

        chatMessagesAdapter = ChatMessagesAdapter()
        chatMessages.adapter = chatMessagesAdapter
        chatMessagesAdapter.setViewModel(chatBotViewModel)

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
            if(motionEvent.action == MotionEvent.ACTION_DOWN)
                startRecordingAudioMessage()

            if(motionEvent.action == MotionEvent.ACTION_UP)
                stopRecordingAudioMessage()

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

    private fun startRecordingAudioMessage() {
        if (!recordAudioPermissionGranted()) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_AUDIO_PERMISSION_REQUEST_CODE)
        } else {
            audioRecordingAlert.visibility = View.VISIBLE

            audioRecorder = MediaRecorder()
            audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
            audioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            outputAudioFilePath = filesDir.absolutePath + "/${UUID.randomUUID()}.amr"
            audioRecorder.setOutputFile(outputAudioFilePath)
            audioRecorder.prepare()
            audioRecorder.start()
        }
    }

    private fun stopRecordingAudioMessage() {
        audioRecordingAlert.visibility = View.GONE

        if(recordAudioPermissionGranted()) {
            audioRecorder.stop()
            audioRecorder.release()

            chatBotViewModel.sendAudioChatMessage(outputAudioFilePath)
        }
    }

    private fun recordAudioPermissionGranted() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED

    private fun scrollToLastMessage() {
        chatMessages.scrollToPosition(chatMessagesAdapter.itemCount - 1)
    }
}
