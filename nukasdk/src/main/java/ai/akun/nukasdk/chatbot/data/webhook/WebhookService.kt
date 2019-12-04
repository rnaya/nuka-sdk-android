package ai.akun.nukasdk.chatbot.data.webhook

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface WebhookService {

    @Multipart
    @POST("api/liveticker")
    fun getLiveMatchUpdates(@Part("sessionID") sessionId: RequestBody,
                            @Part("teamID") teamId: RequestBody,
                            @Part("locale") locale: RequestBody,
                            @Part("match_id") matchId: RequestBody
    ): Single<LiveMatchUpdateResponse>

}

data class LiveMatchUpdateResponse(
    val bookings: List<BookingResponse>?,
    @SerializedName("scorer_away")
    val awayScore: Int?,
    @SerializedName("scorer_home")
    val homeScore: Int?,
    val scorers: List<ScorerResponse>?,
    val substitutions: List<SubstitutionResponse>?,
    @SerializedName("team_away")
    val awayTeam: String?,
    @SerializedName("team_home")
    val homeTeam: String?
)

data class BookingResponse(
    val minute: Int,
    val player: String,
    val team: String,
    @SerializedName("team_id")
    val teamId: String,
    val type: String
)

data class ScorerResponse(
    val minute: Int,
    val player: String,
    val team: String,
    @SerializedName("team_id")
    val teamId: String
)

data class SubstitutionResponse(
    val minute: Int,
    @SerializedName("player_off")
    val playerOff: String,
    @SerializedName("player_on")
    val playerOn: String,
    val reason: String,
    val team: String,
    @SerializedName("team_id")
    val teamId: String
)