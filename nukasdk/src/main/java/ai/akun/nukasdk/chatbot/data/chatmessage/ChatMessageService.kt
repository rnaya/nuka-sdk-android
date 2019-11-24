package ai.akun.nukasdk.chatbot.data.chatmessage

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ChatMessageService {

    @Multipart
    @POST("handler/text.php")
    fun sendTextChatMessage(@Part("sessionID") sessionId: Int,
                            @Part("teamID") teamId: Int,
                            @Part("locale") locale: String,
                            @Part("content") textMessage: String): Single<ChatMessageResponse>

    @Multipart
    @POST("handler/audio.php")
    fun sendAudioChatMessage(@Part("sessionID") sessionId: Int,
                        @Part("teamID") teamId: Int,
                        @Part("locale") locale: String,
                        @Part file: MultipartBody.Part): Single<ChatMessageResponse>
}

data class ChatMessageResponse(
    @SerializedName("fulfillmentText")
    val text: String?
)