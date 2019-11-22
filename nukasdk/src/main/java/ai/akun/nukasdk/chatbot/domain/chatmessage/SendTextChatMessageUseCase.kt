package ai.akun.nukasdk.chatbot.domain.chatmessage

import ai.akun.nukasdk.chatbot.data.chatmessage.ChatMessageRepository
import io.reactivex.Single
import javax.inject.Inject

class SendTextChatMessageUseCase @Inject constructor(private val chatMessageRepository: ChatMessageRepository) {

    //TODO: Review naming. This is requesting an action instead of just sending a message

    fun send(text: String): Single<ChatMessage> {
        val chatMessage = ChatMessage(text, true)
        return chatMessageRepository.sendTextChatMessage(chatMessage)
    }

}