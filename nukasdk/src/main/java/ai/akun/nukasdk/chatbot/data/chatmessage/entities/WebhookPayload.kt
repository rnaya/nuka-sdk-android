package ai.akun.nukasdk.chatbot.data.chatmessage.entities


data class WebhookPayloadEntity(
    val matches: List<MatchEntity>?,
    val players: List<PlayerEntity>?,
    val products: List<ProductEntity>?,
    val articles: List<ArticleEntity>?,
    val rankings: List<RankingEntity>?
)

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

data class PlayerEntity(
    val birthDate: String?,
    val birthplace: String?,
    val height: Int?,
    val identifier: String,
    val joinDate: String?,
    val name: String,
    val position: String?,
    val weight: Int?
)

data class ProductEntity(
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String,
    val website: String
)

data class ArticleEntity(
    val category: String,
    val image: String,
    val publishedAt: String,
    val subtitle: String,
    val title: String,
    val website: String
)

data class RankingEntity(
    val drawn: Int,
    val lost: Int,
    val points: Int,
    val position: Int,
    val teamName: String,
    val teamUid: String,
    val won: Int
)