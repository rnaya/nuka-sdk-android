package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ChatMessageRepository @Inject constructor(private val chatMessageDao: ChatMessageDao,
                                                private val chatMessageMapper: ChatMessageMapper) {

    fun getAllMessages() : Single<List<ChatMessage>> {
        return chatMessageDao.getAll().map { dbEntities -> dbEntities.map { chatMessageMapper.fromDb(it) } }
    }

    fun saveChatMessage(message: ChatMessage): Completable {
        return chatMessageDao.insert(chatMessageMapper.toDb(message))
    }

    fun sendChatMessage(sentMessage: ChatMessage): Single<ChatMessage> {
        val mockChatBotResponseAsEntity = ChatMessageEntity(text = "This is a mock response!", sent = false)
        chatMessageDao.insert(mockChatBotResponseAsEntity)
        return Single.just(chatMessageMapper.fromDb(mockChatBotResponseAsEntity))
    }
}