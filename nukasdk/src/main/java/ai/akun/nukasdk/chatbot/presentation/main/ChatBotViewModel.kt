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
            .subscribe({ messages ->
                this.chatMessages = messages.toMutableList()
                this.chatMessagesLiveData.value = this.chatMessages
            }, {error ->
                Timber.e(error, "Error while sending text message")
            })
            .also {
                disposables.add(it)
            }

    }

    fun sendTextMessage(text: String) {
        val chatMessage = ChatMessage(text, true)
        chatMessageRepository.sendTextChatMessage(chatMessage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onRetrieveMoviesListStart() }
//            .doOnTerminate { onRetrieveMoviesListFinish() }
            .subscribe({ responseMessage ->
                this.chatMessages.add(responseMessage)
                this.chatMessagesLiveData.value = this.chatMessages
            }, { error ->
                Timber.e(error, "Error while sending text message")
            })
            .also {
                disposables.add(it)
            }
    }
}