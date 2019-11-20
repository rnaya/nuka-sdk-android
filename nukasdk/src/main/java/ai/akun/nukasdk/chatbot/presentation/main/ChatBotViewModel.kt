package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessage
import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessageType
import ai.akun.nukasdk.chatbot.domain.chatmessage.SendTextChatMessageUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ChatBotViewModel(private val sendTextChatMessageUseCase: SendTextChatMessageUseCase) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val chatMessages: MutableList<ChatMessage> = mutableListOf()
    private val chatMessagesLiveData = MutableLiveData<MutableList<ChatMessage>>()

    fun onChatMessagesUpdated(): LiveData<MutableList<ChatMessage>> = chatMessagesLiveData

    fun fetchMessages() {
        //TODO add FetchMessagesUseCase
        chatMessagesLiveData.value = mutableListOf(ChatMessage("This is a mock message", ChatMessageType.SENT))
    }

    fun sendTextMessage(text: String) {
        sendTextChatMessageUseCase
            .send(text)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ message ->
                this.chatMessages.add(message)
                this.chatMessagesLiveData.value = this.chatMessages
            }, {error ->
                Timber.e(error, "Error while sending text message")
            })
            .also {
                disposables.add(it)
            }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val sendTextChatMessageUseCase: SendTextChatMessageUseCase) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ChatBotViewModel(
                sendTextChatMessageUseCase
            ) as T
        }
    }
}