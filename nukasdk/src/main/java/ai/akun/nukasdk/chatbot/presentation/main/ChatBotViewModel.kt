package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.BuildConfig
import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageRepository
import ai.akun.nukasdk.chatbot.data.webhook.WebhookService
import android.content.Context
import android.os.Handler
import androidx.core.os.ConfigurationCompat
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

    //TODO get values
    private val sessionId = 1
    private val teamId = 1

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
                    addLocallyReceivedChatMessage(context.getString(R.string.chatbot_welcome_message), ChatMessageIntent.RECEIVED_WELCOME)
                }
            }, { error ->
                Timber.e(error, "Error while getting chat messages")
            })
            .also { disposables.add(it) }
    }

    fun addLocallyReceivedChatMessage(text: String, chatMessageIntent: ChatMessageIntent) {
        val chatMessage = ChatMessage(
            text = text,
            intent = chatMessageIntent
        )
        saveChatMessage(chatMessage)
    }

    fun sendTextChatMessage(text: String) {
        val chatMessage = ChatMessage(text = text, intent = ChatMessageIntent.SENT_TEXT)
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
                if(chatMessage.intent != ChatMessageIntent.SENT_TEXT && chatMessage.intent != ChatMessageIntent.SENT_AUDIO)
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
            .sendTextChatMessage(chatMessage, getCurrentLocale(), sessionId, teamId)
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
        val chatMessage = ChatMessage(audioFilePath = audioFilePath, intent = ChatMessageIntent.SENT_AUDIO)
        saveChatMessage(chatMessage)

        Handler().postDelayed({
            setLoading(true)
            sendAudioChatMessageToServer(chatMessage)
        }, 1500)
    }

    private fun sendAudioChatMessageToServer(chatMessage: ChatMessage) {
        chatMessageRepository
            .sendAudioChatMessage(chatMessage, getCurrentLocale(), sessionId, teamId)
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

    private fun getCurrentLocale(): String {
        return if (BuildConfig.DEBUG) "es" else ConfigurationCompat.getLocales(context.resources.configuration)[0].language
    }

    fun sendLiveMatchUpdatesRequest(matchId: String) {
        chatMessageRepository
            .sendLiveMatchUpdatesRequest(
                getCurrentLocale(),
                sessionId,
                teamId,
                matchId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ botChatMessageResponse ->
                createLiveMatchMockSimulation(botChatMessageResponse.liveMatchUpdate!!)
            }, { error ->
                setLoading(false)
                Timber.e(error, "Error while sending chat message")
            })
            .also { disposables.add(it) }
    }

    private fun createLiveMatchMockSimulation(liveMatchUpdate: LiveMatchUpdate) {
        val liveMatchUpdateChatMessage = ChatMessage(intent = ChatMessageIntent.RECEIVED_LIVE_MATCH_UPDATE)
        Handler().postDelayed({
            saveChatMessage(liveMatchUpdateChatMessage.copy(liveMatchUpdate = LiveMatchUpdate(
                bookings = liveMatchUpdate.bookings,
                homeTeam = liveMatchUpdate.homeTeam,
                awayTeam = liveMatchUpdate.awayTeam
            )))
        }, 2000)

        Handler().postDelayed({
            saveChatMessage(liveMatchUpdateChatMessage.copy(liveMatchUpdate = LiveMatchUpdate(
                scorers = liveMatchUpdate.scorers,
                homeTeam = liveMatchUpdate.homeTeam,
                awayTeam = liveMatchUpdate.awayTeam
            )))
        }, 4000)

        Handler().postDelayed({
            saveChatMessage(liveMatchUpdateChatMessage.copy(liveMatchUpdate = LiveMatchUpdate(
                substitutions= liveMatchUpdate.substitutions,
                homeTeam = liveMatchUpdate.homeTeam,
                awayTeam = liveMatchUpdate.awayTeam
            )))
        }, 6000)
    }
}