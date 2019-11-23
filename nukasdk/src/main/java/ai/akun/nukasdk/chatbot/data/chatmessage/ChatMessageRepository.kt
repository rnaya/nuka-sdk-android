package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import io.reactivex.Completable
import io.reactivex.Single
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

    fun sendChatMessage(sentMessage: ChatMessage): Single<ChatMessage> {
        return chatMessageService
                .sendTextMessage(1, 1, "es-AR", sentMessage.text!!) //TODO get values
            .map { chatMessageMapper.fromResponse(it) }
    }
}