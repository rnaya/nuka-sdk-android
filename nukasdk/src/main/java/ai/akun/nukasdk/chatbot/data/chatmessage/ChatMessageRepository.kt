package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class ChatMessageRepository @Inject constructor(private val chatMessageDao: ChatMessageDao,
                                                private val chatMessageMapper: ChatMessageMapper,
                                                private val chatMessageService: ChatMessageService) {

    fun getAllMessages() : Single<List<ChatMessage>> {
        return chatMessageDao.getAll().map { dbEntities -> dbEntities.map { chatMessageMapper.fromDb(it) } }
    }

    fun saveChatMessage(message: ChatMessage): Completable {
        return chatMessageDao.insert(chatMessageMapper.toDb(message))
    }

    fun sendTextChatMessage(
        sentMessage: ChatMessage,
        locale: String,
        sessionId: Int,
        teamId: Int
    ): Single<ChatMessage> {
        return chatMessageService
            .sendTextChatMessage(sessionId, teamId, locale, sentMessage.text!!)
            .map { chatMessageMapper.fromResponse(it) }
    }

    fun sendAudioChatMessage(
        sentMessage: ChatMessage,
        localeCode: String,
        sessionId: Int,
        teamId: Int
    ): Single<ChatMessage> {
        val file = File(sentMessage.audioFilePath!!)
        val requestFile = file.asRequestBody("audio/amr".toMediaTypeOrNull())
        val filePart = MultipartBody.Part.createFormData("audio", file.name, requestFile)
        val sessionIdPart = sessionId.toString().toRequestBody(MultipartBody.FORM)
        val teamIdPart = teamId.toString().toRequestBody(MultipartBody.FORM)
        val localePart = localeCode.toRequestBody(MultipartBody.FORM)

        return chatMessageService
            .sendAudioChatMessage(sessionIdPart, teamIdPart, localePart, filePart)
            .map { chatMessageMapper.fromResponse(it) }
    }
}