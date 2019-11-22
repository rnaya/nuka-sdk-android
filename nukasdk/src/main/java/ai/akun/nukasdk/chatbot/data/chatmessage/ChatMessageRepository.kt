package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.domain.chatmessage.ChatMessage
import io.reactivex.Single
import javax.inject.Inject

class ChatMessageRepository @Inject constructor(private val chatMessageDao: ChatMessageDao,
                                                private val chatMessageMapper: ChatMessageMapper) {

    //TODO: this should use live data so the ViewModel can subscribe and update each time a new message is saved
    fun getAllMessages() : Single<List<ChatMessage>> {
        return chatMessageDao.getAll().map { dbEntities -> dbEntities.map { chatMessageMapper.fromDb(it) } }
    }

    //TODO: This should only save sent message, make the request and save de response (the rest should be handled with live data)
    fun sendTextChatMessage(sentMessage: ChatMessage) : Single<ChatMessage> {
        val mockChatBotResponseAsEntity = ChatMessageEntity(text = "This is a mock response!", sent = false)

        return chatMessageDao
            .insert(chatMessageMapper.toDb(sentMessage))
            //TODO: Send to server and return bot ChatMessage as response
            //First, we should receive a sentMessage response
            //Second, we should map to ChatMessageEntity and save
            .concatWith(chatMessageDao.insert(mockChatBotResponseAsEntity))
            .andThen(Single.defer { Single.just(chatMessageMapper.fromDb(mockChatBotResponseAsEntity)) })
    }
}