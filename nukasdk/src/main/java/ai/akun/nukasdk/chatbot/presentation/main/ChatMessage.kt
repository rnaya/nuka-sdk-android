package ai.akun.nukasdk.chatbot.presentation.main

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.*
import ai.akun.nukasdk.chatbot.presentation.chatmessage.holder.SocialNetworksViewHolder
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.view.ViewGroup

data class ChatMessage(
    val text: String? = null,
    val audioFilePath: String? = null,
    val intent: ChatMessageIntent,
    val cardsPayload: List<Card>? = null,
    val webhookPayload: WebhookPayload? = null,
    val liveMatchUpdate: LiveMatchUpdate? = null
    )


data class Card(
    val title: String,
    val subtitle: String,
    val imageUri: String,
    val buttons: List<Button>?
)

data class Button(
    val text: String,
    val postback: String?
)

data class WebhookPayload(
    val matches: List<Match>?,
    val players: List<Player>?,
    val products: List<Product>?,
    val articles: List<Article>?,
    val rankings: List<Ranking>?
)

data class Match(
    val competition: String,
    val identifier: String,
    val scheduledDate: String,
    val awayTeam: Team,
    val homeTeam: Team,
    val tickets: String?,
    val venue: Venue
)

data class Team(
    val identifier: String,
    val name: String,
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

data class Article(
    val category: String,
    val image: String,
    val publishedAt: String,
    val subtitle: String,
    val title: String,
    val website: String
)

data class Ranking(
    val drawn: Int,
    val lost: Int,
    val points: Int,
    val position: Int,
    val teamName: String,
    val teamUid: String,
    val won: Int
)

enum class ChatMessageIntent(val displayName: String) : ViewHolderSource {
    SENT_TEXT("nuka.sent.text") {
        override fun getViewType() = 1

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_sent_text_chat_message, false)
            return SentTextViewHolder(inflatedView)
        }
    },
    SENT_AUDIO("nuka.sent.audio") {
        override fun getViewType() = 2

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_sent_audio_chat_message, false)
            return AudioViewHolder(inflatedView)
        }
    },
    RECEIVED_WELCOME("nuka.welcome") {
        override fun getViewType() = 3

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView =
                parent.inflate(R.layout.item_row_received_welcome_text_chat_message, false)
            return WelcomeViewHolder(inflatedView)
        }
    },
    RECEIVED_TEXT("nuka.text") {
        override fun getViewType() = 4

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_received_text_chat_message, false)
            return ReceivedTextViewHolder(inflatedView)
        }
    },
    RECEIVED_GIF("nuka.botactions.giphy") {
        override fun getViewType() = 5

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_received_gif_chat_message, false)
            return GifViewHolder(inflatedView)
        }
    },
    RECEIVED_SOCIAL_NETWORKS("nuka.player.socialnetworks") {
        override fun getViewType() = 6

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_scrollable_chat_message, false)
            return SocialNetworksViewHolder(
                inflatedView
            )
        }
    },
    RECEIVED_SPOTIFY("nuka.spotify") {
        override fun getViewType() = 7

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_scrollable_chat_message, false)
            return SocialNetworksViewHolder(
                inflatedView
            )
        }
    },
    RECEIVED_MATCHES("nuka.matches.next") {
        override fun getViewType() = 8

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_scrollable_chat_message, false)
            return NextMatchesViewHolder(
                inflatedView
            )
        }
    },
    RECEIVED_PLAYERS("nuka.players.list") {
        override fun getViewType() = 9

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_scrollable_chat_message, false)
            return PlayersViewHolder(
                inflatedView
            )
        }
    },
    RECEIVED_PRODUCTS("nuka.buy.fanshop") {
        override fun getViewType() = 10

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_scrollable_chat_message, false)
            return ProductsViewHolder(
                inflatedView
            )
        }
    },
    RECEIVED_ARTICLES("nuka.articles") {
        override fun getViewType() = 11

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_scrollable_chat_message, false)
            return ArticlesViewHolder(
                inflatedView
            )
        }
    },
    RECEIVED_RANKING("nuka.ranking") {
        override fun getViewType() = 12

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView = parent.inflate(R.layout.item_row_received_ranking_chat_message, false)
            return RankingViewHolder(
                inflatedView
            )
        }
    },
    RECEIVED_LIVE_MATCH_AVAILABLE("nuka.match.updates") {
        override fun getViewType() = 13

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView =
                parent.inflate(R.layout.item_row_received_live_match_available_text_chat_message, false)
            return LiveMatchAvailableViewHolder(inflatedView)
        }
    },
    RECEIVED_LIVE_MATCH_UPDATE("nuka.match.update") {
        override fun getViewType() = 14

        override fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder {
            val inflatedView =
                parent.inflate(R.layout.item_row_match_update, false)
            return LiveMatchUpdateViewHolder(inflatedView)
        }
    }
}

interface ViewHolderSource {
    fun getViewType(): Int
    fun getViewHolder(parent: ViewGroup): ChatMessageViewHolder
}