package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class ChatMessageRepository @Inject constructor(private val chatMessageDao: ChatMessageDao,
                                                private val chatMessageMapper: ChatMessageMapper,
                                                private val chatMessageService: ChatMessageService) {

    //TODO get values
    private val sessionId = 1
    private val teamId = 1
    private val locale = "es-AR"

    fun getAllMessages() : Single<List<ChatMessage>> {
        return chatMessageDao.getAll().map { dbEntities -> dbEntities.map { chatMessageMapper.fromDb(it) } }
    }

    fun saveChatMessage(message: ChatMessage): Completable {
        return chatMessageDao.insert(chatMessageMapper.toDb(message))
    }

    fun sendTextChatMessage(sentMessage: ChatMessage): Single<ChatMessage> {
        return chatMessageService
                .sendTextChatMessage(sessionId, teamId, locale, sentMessage.text!!)
            .map { chatMessageMapper.fromResponse(it) }
    }

    fun sendAudioChatMessage(sentMessage: ChatMessage): Single<ChatMessage> {
        val file = File(sentMessage.audioFilePath!!)
        val requestFile = file.asRequestBody("audio/3gpp".toMediaTypeOrNull())
        val part = MultipartBody.Part.createFormData("audio", file.name, requestFile)

        return chatMessageService
            .sendAudioChatMessage(sessionId, teamId, locale, part)
            .map { chatMessageMapper.fromResponse(it) }
    }
}