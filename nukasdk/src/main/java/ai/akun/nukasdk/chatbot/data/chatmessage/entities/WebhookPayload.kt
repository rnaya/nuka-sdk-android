package ai.akun.nukasdk.chatbot.data.chatmessage.entities

data class MatchEntity(
    val competition: String,
    val identifier: String,
    val scheduledDate: String,
    val awayTeam: TeamEntity,
    val homeTeam: TeamEntity,
    val tickets: String?,
    val venue: VenueEntity
)

data class TeamEntity(
    val identifier: String,
    val name: String,
    val shortName: String
)

data class VenueEntity(
    val address: String?,
    val identifier: String,
    val latitude: Double?,
    val longitude: Double?,
    val name: String
)