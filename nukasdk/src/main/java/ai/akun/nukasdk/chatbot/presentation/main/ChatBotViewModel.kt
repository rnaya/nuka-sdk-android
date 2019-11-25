package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageRepository
import android.content.Context
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ChatBotViewModel @Inject constructor(private val context: Context,
                                           private val chatMessageRepository: ChatMessageRepository) : ViewModel() {

    private val disposables = CompositeDisposable()
    private var chatMessages: MutableList<ChatMessage> = mutableListOf()
    private val chatMessagesLiveData = MutableLiveData<MutableList<ChatMessage>>()
    private val loadingLiveData = MutableLiveData<Boolean>()

    fun onChatMessagesUpdated(): LiveData<MutableList<ChatMessage>> = chatMessagesLiveData

    fun fetchMessages() {
        chatMessageRepository
            .getAllMessages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ chatMessages ->
                this.chatMessages = chatMessages.toMutableList()
                this.chatMessagesLiveData.value = this.chatMessages

                if (this.chatMessages.count() == 0) {
                    addWelcomeChatMessage()
                }
            }, { error ->
                Timber.e(error, "Error while getting chat messages")
            })
            .also { disposables.add(it) }
    }

    private fun addWelcomeChatMessage() {
        val welcomeChatMessage = ChatMessage(
            text = context.getString(R.string.welcome_message),
            intent = ChatMessageIntent.WELCOME.description
        )
        saveChatMessage(welcomeChatMessage)
    }

    fun sendTextChatMessage(text: String) {
        val chatMessage = ChatMessage(text = text)
        saveChatMessage(chatMessage)

        Handler().postDelayed({
            setLoading(true)
            sendTextChatMessageToServer(chatMessage)
        }, 1500)
    }

    private fun saveChatMessage(chatMessage: ChatMessage) {
        chatMessageRepository.saveChatMessage(chatMessage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if(chatMessage.intent != null)
                    setLoading(false)

                this.chatMessages.add(chatMessage)
                this.chatMessagesLiveData.value = this.chatMessages
            }, { error ->
                setLoading(false)
                Timber.e(error, "Error while saving text message")
            })
            .also { disposables.add(it) }
    }

    private fun sendTextChatMessageToServer(chatMessage: ChatMessage) {
        chatMessageRepository
            .sendTextChatMessage(chatMessage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ botChatMessageResponse ->
                saveChatMessage(botChatMessageResponse)
            }, { error ->
                setLoading(false)
                Timber.e(error, "Error while sending chat message")
            })
            .also { disposables.add(it) }
    }

    fun onLoading() = loadingLiveData

    private fun setLoading(isLoading: Boolean) {
        loadingLiveData.value = isLoading
    }

    fun sendAudioChatMessage(audioFilePath: String) {
        val chatMessage = ChatMessage(audioFilePath = audioFilePath)
        saveChatMessage(chatMessage)

        Handler().postDelayed({
            setLoading(true)
            sendAudioChatMessageToServer(chatMessage)
        }, 1500)
    }

    private fun sendAudioChatMessageToServer(chatMessage: ChatMessage) {
        chatMessageRepository
            .sendAudioChatMessage(chatMessage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ botChatMessageResponse ->
                saveChatMessage(botChatMessageResponse)
            }, { error ->
                setLoading(false)
                Timber.e(error, "Error while sending chat message")
            })
            .also { disposables.add(it) }
    }
}