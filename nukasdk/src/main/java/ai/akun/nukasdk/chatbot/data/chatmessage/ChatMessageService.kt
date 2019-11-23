package ai.akun.nukasdk.chatbot.data.chatmessage

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ChatMessageService {

    @Multipart
    @POST("handler/text.php")
    fun sendTextMessage(@Part("sessionID") sessionId: Int,
                        @Part("teamID") teamId: Int,
                        @Part("locale") locale: String,
                        @Part("content") textMessage: String): Single<ChatMessageResponse>
}

data class ChatMessageResponse(
    @SerializedName("fulfillmentText")
    val text: String?
)