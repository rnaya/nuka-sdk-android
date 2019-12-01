package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.data.chatmessage.entities.*
import ai.akun.nukasdk.chatbot.presentation.main.Button
import ai.akun.nukasdk.chatbot.presentation.main.Card
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import ai.akun.nukasdk.chatbot.presentation.main.Match
import ai.akun.nukasdk.chatbot.presentation.main.Player
import ai.akun.nukasdk.chatbot.presentation.main.Product
import ai.akun.nukasdk.chatbot.presentation.main.Team
import ai.akun.nukasdk.chatbot.presentation.main.Venue
import ai.akun.nukasdk.chatbot.presentation.main.WebhookPayload

class ChatMessageMapper {

    fun toDb(from: ChatMessage) =
        ChatMessageEntity(
            text = from.text,
            audioFilePath = from.audioFilePath,
            intent = from.intent.displayName,
            cardPayload = from.cardsPayload?.map {
                CardEntity(
                    it.title,
                    it.subtitle,
                    it.imageUri,
                    it.buttons?.map { button ->
                        ButtonEntity(
                            button.text,
                            button.postback
                        )
                    }
                )
            },
            webhookPayload = WebhookPayloadEntity(
                from.webhookPayload?.matches?.map {
                    MatchEntity(
                        it.competition,
                        it.identifier,
                        it.scheduledDate,
                        TeamEntity(it.awayTeam.identifier, it.awayTeam.name, it.awayTeam.shortName),
                        TeamEntity(it.homeTeam.identifier, it.homeTeam.name, it.homeTeam.shortName),
                        it.tickets,
                        VenueEntity(it.venue.address, it.venue.identifier, it.venue.latitude, it.venue.longitude, it.venue.name)
                    )
                },
                from.webhookPayload?.players?.map {
                    PlayerEntity(
                        it.birthDate,
                        it.birthplace,
                        it.height,
                        it.identifier,
                        it.joinDate,
                        it.name,
                        it.position,
                        it.weight
                    )
                },
                from.webhookPayload?.products?.map {
                    ProductEntity(
                        it.category,
                        it.description,
                        it.image,
                        it.price,
                        it.title,
                        it.website
                    )
                }
            )
        )

    fun fromDb(from: ChatMessageEntity) = ChatMessage(
        from.text,
        from.audioFilePath,
        ChatMessageIntent.values().firstOrNull { it.displayName == from.intent } ?: ChatMessageIntent.RECEIVED_TEXT,
        from.cardPayload?.map {
            Card(
                it.title,
                it.subtitle,
                it.imageUri,
                it.buttons?.map {button ->
                    Button(
                        button.text,
                        button.postback
                    )
                }
            )
        },
        webhookPayload = WebhookPayload(
            from.webhookPayload?.matches?.map {
                Match(
                    it.competition,
                    it.identifier,
                    it.scheduledDate,
                    Team(it.awayTeam.identifier, it.awayTeam.name, it.awayTeam.shortName),
                    Team(it.homeTeam.identifier, it.homeTeam.name, it.homeTeam.shortName),
                    it.tickets,
                    Venue(it.venue.address, it.venue.identifier, it.venue.latitude, it.venue.longitude, it.venue.name)
                )
            },
            from.webhookPayload?.players?.map {
                Player(
                    it.birthDate,
                    it.birthplace,
                    it.height,
                    it.identifier,
                    it.joinDate,
                    it.name,
                    it.position,
                    it.weight
                )
            },
            from.webhookPayload?.products?.map {
                Product(
                    it.category,
                    it.description,
                    it.image,
                    it.price,
                    it.title,
                    it.website
                )
            }
        )
    )

    fun fromResponse(from: ChatMessageResponse) = ChatMessage(
        text = from.text,
        intent = ChatMessageIntent.values().firstOrNull { it.displayName == from.intent.displayName } ?: ChatMessageIntent.RECEIVED_TEXT,
        cardsPayload = from.fulfillmentMessages?.map {
            Card(
                it.card?.title ?: "",
                it.card?.subtitle ?: "",
                it.card?.imageUri ?: "",
                it.card?.buttons?.map { button ->
                    Button(button.text, button.postback)
                }) },
        webhookPayload = WebhookPayload(
            from.webhookPayload?.matches?.map {
                Match(
                    it.competition,
                    it.identifier,
                    it.scheduledDate,
                    Team(it.awayTeam.identifier, it.awayTeam.name, it.awayTeam.shortName),
                    Team(it.homeTeam.identifier, it.homeTeam.name, it.homeTeam.shortName),
                    it.tickets,
                    Venue(it.venue.address, it.venue.identifier, it.venue.latitude, it.venue.longitude, it.venue.name)
                )
            },
            from.webhookPayload?.players?.map {
                Player(
                    it.birthDate,
                    it.birthplace,
                    it.height,
                    it.identifier,
                    it.joinDate,
                    it.name,
                    it.position,
                    it.weight
                )
            },
            from.webhookPayload?.products?.map {
                Product(
                    it.category,
                    it.description,
                    it.image,
                    it.price,
                    it.title,
                    it.website
                )
            }
        )
    )
}