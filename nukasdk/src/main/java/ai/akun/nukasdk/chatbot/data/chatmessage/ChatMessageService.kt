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
    val fulfillmentMessages: List<FulfillmentMessage>?,
    val webhookPayload: WebhookPayload?
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

data class WebhookPayload(
    val matches: List<Match>?,
    val players: List<Player>?,
    val products: List<Product>?
)

data class Match(
    val competition: String,
    val identifier: String,
    @SerializedName("scheduled_date")
    val scheduledDate: String,
    @SerializedName("team_away")
    val awayTeam: Team,
    @SerializedName("team_home")
    val homeTeam: Team,
    val tickets: String?,
    val venue: Venue
)

data class Team(
    val identifier: String,
    val name: String,
    @SerializedName("short_name")
    val shortName: String
)

data class Venue(
    val address: String?,
    val identifier: String,
    val latitude: Double?,
    val longitude: Double?,
    val name: String
)

data class Player(
    @SerializedName("birthdate")
    val birthDate: String?,
    val birthplace: String?,
    val height: Int?,
    val identifier: String,
    val joinDate: String?,
    val name: String,
    val position: String?,
    val weight: Int?
)

data class Product(
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String,
    val website: String
)