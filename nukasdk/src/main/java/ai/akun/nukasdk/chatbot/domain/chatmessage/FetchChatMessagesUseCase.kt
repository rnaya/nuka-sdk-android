package ai.akun.nukasdk.chatbot.domain.chatmessage

import io.reactivex.Observable

class FetchChatMessagesUseCase {

    fun fetch(): Observable<List<ChatMessage>> {
        val mockMessages = listOf(ChatMessage("This is a mock message", ChatMessageType.SENT))
        return Observable.just(mockMessages )
    }
}