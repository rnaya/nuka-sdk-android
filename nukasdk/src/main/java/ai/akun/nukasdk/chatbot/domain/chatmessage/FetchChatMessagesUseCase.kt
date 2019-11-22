package ai.akun.nukasdk.chatbot.domain.chatmessage

import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageRepository
import io.reactivex.Single
import javax.inject.Inject

class FetchChatMessagesUseCase @Inject constructor(private val chatMessageRepository: ChatMessageRepository) {

    fun fetch(): Single<List<ChatMessage>> {
        return chatMessageRepository.getAllMessages()
    }
}