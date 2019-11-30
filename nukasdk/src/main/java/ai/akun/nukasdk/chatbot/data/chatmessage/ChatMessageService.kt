package ai.akun.nukasdk.chatbot.data.chatmessage

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ChatMessageService {

    @Multipart
    @POST("handler/text.php")
    fun sendTextChatMessage(@Part("sessionID") sessionId: RequestBody,
                            @Part("teamID") teamId: RequestBody,
                            @Part("locale") locale: RequestBody,
                            @Part("content") textMessage: RequestBody): Single<ChatMessageResponse>

    @Multipart
    @POST("handler/audio-android.php")
    fun sendAudioChatMessage(@Part("sessionID") sessionId: RequestBody,
                             @Part("teamID") teamId: RequestBody,
                             @Part("locale") locale: RequestBody,
                             @Part file: MultipartBody.Part): Single<ChatMessageResponse>
}

data class ChatMessageResponse(
    @SerializedName("fulfillmentText")
    val text: String?,
    val intent: ChatMessageIntent,
    val fulfillmentMessages: List<FulfillmentMessage>?
)

data class ChatMessageIntent(
    val name: String,
    val displayName: String
)


data class FulfillmentMessage(
    val card: Card?
)

data class Card(
    val title: String,
    val subtitle: String,
    val imageUri: String,
    val buttons: List<Button>?
)

data class Button(
    val text: String,
    val postback: String
)