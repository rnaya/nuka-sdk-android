package ai.akun.nukasdk.chatbot.domain.chatmessage

import io.reactivex.Observable

class SendTextChatMessageUseCase {

    fun send(text: String): Observable<ChatMessage> {
        return Observable.just(ChatMessage(text, ChatMessageType.SENT))
    }
}