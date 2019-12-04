package ai.akun.nukasdk.chatbot.presentation.main

data class LiveMatchUpdate(
    val bookings: List<Booking>? = null,
    val awayScore: Int? = null,
    val homeScore: Int? = null,
    val scorers: List<Scorer>? = null,
    val substitutions: List<Substitution>? = null,
    val awayTeam: String? = null,
    val homeTeam: String? = null
)

data class Booking(
    val minute: Int,
    val player: String,
    val team: String,
    val teamId: String,
    val type: String
)

data class Scorer(
    val minute: Int,
    val player: String,
    val team: String,
    val teamId: String
)

data class Substitution(
    val minute: Int,
    val playerOff: String,
    val playerOn: String,
    val reason: String,
    val team: String,
    val teamId: String
)