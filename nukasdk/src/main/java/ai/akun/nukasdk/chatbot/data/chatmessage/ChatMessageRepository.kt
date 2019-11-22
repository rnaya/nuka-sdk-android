package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ChatMessageRepository @Inject constructor(private val chatMessageDao: ChatMessageDao,
                                                private val chatMessageMapper: ChatMessageMapper) {

    fun getAllMessages() : Single<List<ChatMessageEntity>> {
        return chatMessageDao.getAll()
    }

    fun saveChatMessage(message: ChatMessage): Completable {
        return chatMessageDao.insert(chatMessageMapper.toDb(message))
    }

    fun sendTextChatMessage(sentMessage: ChatMessage) {
        //TODO: Send message to server and insert response as ChatMessageEntity
        val mockChatBotResponseAsEntity = ChatMessageEntity(text = "This is a mock response!", sent = false)
        chatMessageDao.insert(mockChatBotResponseAsEntity)
    }
}