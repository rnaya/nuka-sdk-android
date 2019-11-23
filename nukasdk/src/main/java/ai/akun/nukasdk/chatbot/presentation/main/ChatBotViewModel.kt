package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class ChatBotViewModel @Inject constructor(private val chatMessageRepository: ChatMessageRepository) : ViewModel() {

    private val disposables = CompositeDisposable()
    private var chatMessages: MutableList<ChatMessage> = mutableListOf()
    private val chatMessagesLiveData = MutableLiveData<MutableList<ChatMessage>>()

    fun onChatMessagesUpdated(): LiveData<MutableList<ChatMessage>> = chatMessagesLiveData

    fun fetchMessages() {
        chatMessageRepository
            .getAllMessages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ chatMessages ->
                this.chatMessages = chatMessages.toMutableList()
                this.chatMessagesLiveData.value = this.chatMessages
            }, {error ->
                Timber.e(error, "Error while getting chat messages")
            })
            .also { disposables.add(it) }
    }

    fun sendTextChatMessage(text: String) {
        val chatMessage = ChatMessage(text, true)
        saveChatMessage(chatMessage)
        sendChatMessage(chatMessage)
    }

    private fun saveChatMessage(chatMessage: ChatMessage) {
        chatMessageRepository.saveChatMessage(chatMessage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this.chatMessages.add(chatMessage)
                this.chatMessagesLiveData.value = this.chatMessages
            }, { error ->
                Timber.e(error, "Error while saving text message")
            })
            .also { disposables.add(it) }
    }

    private fun sendChatMessage(chatMessage: ChatMessage) {
        chatMessageRepository
            .sendChatMessage(chatMessage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ botChatMessageResponse ->
                saveChatMessage(botChatMessageResponse)
            }, { error ->
                Timber.e(error, "Error while sending chat message")
            })
            .also { disposables.add(it) }
    }
}