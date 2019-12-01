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
    val intent: ChatMessageIntentResponse,
    val fulfillmentMessages: List<FulfillmentMessageResponse>?,
    val webhookPayload: WebhookPayloadResponse?
)

data class ChatMessageIntentResponse(
    val name: String,
    val displayName: String
)

data class FulfillmentMessageResponse(
    val card: CardResponse?
)

data class CardResponse(
    val title: String,
    val subtitle: String,
    val imageUri: String,
    val buttons: List<ButtonResponse>?
)

data class ButtonResponse(
    val text: String,
    val postback: String
)

data class WebhookPayloadResponse(
    val matches: List<MatchResponse>?,
    val players: List<PlayerResponse>?,
    val products: List<ProductResponse>?,
    val articles: List<ArticleResponse>?,
    val rankings: List<RankingResponse>?
)

data class MatchResponse(
    val competition: String,
    val identifier: String,
    @SerializedName("scheduled_date")
    val scheduledDate: String,
    @SerializedName("team_away")
    val awayTeam: TeamResponse,
    @SerializedName("team_home")
    val homeTeam: TeamResponse,
    val tickets: String?,
    val venue: VenueResponse
)

data class TeamResponse(
    val identifier: String,
    val name: String,
    @SerializedName("short_name")
    val shortName: String
)

data class VenueResponse(
    val address: String?,
    val identifier: String,
    val latitude: Double?,
    val longitude: Double?,
    val name: String
)

data class PlayerResponse(
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

data class ProductResponse(
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String,
    val website: String
)

data class ArticleResponse(
    val category: String,
    val image: String,
    val publishedAt: String,
    val subtitle: String,
    val title: String,
    val website: String
)

data class RankingResponse(
    val drawn: Int,
    val lost: Int,
    val points: Int,
    val position: Int,
    val teamName: String,
    val teamUid: String,
    val won: Int
)