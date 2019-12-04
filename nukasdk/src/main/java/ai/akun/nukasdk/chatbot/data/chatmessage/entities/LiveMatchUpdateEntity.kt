package ai.akun.nukasdk.chatbot.data.chatmessage.entities

data class LiveMatchUpdateEntity(
    val bookings: List<BookingEntity>?,
    val awayScore: Int?,
    val homeScore: Int?,
    val scorers: List<ScorerEntity>?,
    val substitutions: List<SubstitutionEntity>?,
    val awayTeam: String?,
    val homeTeam: String?
)

data class BookingEntity(
    val minute: Int,
    val player: String,
    val team: String,
    val teamId: String,
    val type: String
)

data class ScorerEntity(
    val minute: Int,
    val player: String,
    val team: String,
    val teamId: String
)

data class SubstitutionEntity(
    val minute: Int,
    val playerOff: String,
    val playerOn: String,
    val reason: String,
    val team: String,
    val teamId: String
)