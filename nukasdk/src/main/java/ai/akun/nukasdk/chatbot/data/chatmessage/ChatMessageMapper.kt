package ai.akun.nukasdk.chatbot.data.chatmessage

import ai.akun.nukasdk.chatbot.data.chatmessage.entities.*
import ai.akun.nukasdk.chatbot.data.webhook.LiveMatchUpdateResponse
import ai.akun.nukasdk.chatbot.presentation.main.*

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
                },
                from.webhookPayload?.articles?.map {
                    ArticleEntity(
                        it.category,
                        it.image,
                        it.publishedAt,
                        it.subtitle,
                        it.title,
                        it.website
                    )
                },
                from.webhookPayload?.rankings?.map {
                    RankingEntity(
                        it.drawn,
                        it.lost,
                        it.points,
                        it.position,
                        it.teamName,
                        it.teamUid,
                        it.won
                    )
                }
            ),
            liveMatchUpdateEntity = LiveMatchUpdateEntity(
                from.liveMatchUpdate?.bookings?.map {
                    BookingEntity(
                        it.minute,
                        it.player,
                        it.team,
                        it.teamId,
                        it.type
                    )
                },
                from.liveMatchUpdate?.awayScore,
                from.liveMatchUpdate?.homeScore,
                from.liveMatchUpdate?.scorers?.map {
                    ScorerEntity(
                        it.minute,
                        it.player,
                        it.team,
                        it.teamId
                    )
                },
                from.liveMatchUpdate?.substitutions?.map {
                    SubstitutionEntity(
                        it.minute,
                        it.playerOff,
                        it.playerOn,
                        it.reason,
                        it.team,
                        it.teamId
                    )
                },
                from.liveMatchUpdate?.awayTeam,
                from.liveMatchUpdate?.homeTeam
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
            },
            from.webhookPayload?.articles?.map {
                Article(
                    it.category,
                    it.image,
                    it.publishedAt,
                    it.subtitle,
                    it.title,
                    it.website
                )
            },
            from.webhookPayload?.rankings?.map {
                Ranking(
                    it.drawn,
                    it.lost,
                    it.points,
                    it.position,
                    it.teamName,
                    it.teamUid,
                    it.won
                )
            }
        ),
        liveMatchUpdate = LiveMatchUpdate(
            from.liveMatchUpdateEntity?.bookings?.map {
                Booking(
                    it.minute,
                    it.player,
                    it.team,
                    it.teamId,
                    it.type
                )
            },
            from.liveMatchUpdateEntity?.awayScore,
            from.liveMatchUpdateEntity?.homeScore,
            from.liveMatchUpdateEntity?.scorers?.map {
                Scorer(
                    it.minute,
                    it.player,
                    it.team,
                    it.teamId
                )
            },
            from.liveMatchUpdateEntity?.substitutions?.map {
                Substitution(
                    it.minute,
                    it.playerOff,
                    it.playerOn,
                    it.reason,
                    it.team,
                    it.teamId
                )
            },
            from.liveMatchUpdateEntity?.awayTeam,
            from.liveMatchUpdateEntity?.homeTeam
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
            },
            from.webhookPayload?.articles?.map {
                Article(
                    it.category,
                    it.image,
                    it.publishedAt,
                    it.subtitle,
                    it.title,
                    it.website
                )
            },
            from.webhookPayload?.rankings?.map {
                Ranking(
                    it.drawn,
                    it.lost,
                    it.points,
                    it.position,
                    it.teamName,
                    it.teamUid,
                    it.won
                )
            }
        )
    )

    fun fromLiveMatchUpdate(from: LiveMatchUpdateResponse) = ChatMessage(
        liveMatchUpdate = LiveMatchUpdate(
            from.bookings?.map {
                Booking(
                    it.minute,
                    it.player,
                    it.team,
                    it.teamId,
                    it.type
                )
            },
            from.awayScore,
            from.homeScore,
            from.scorers?.map {
                Scorer(
                    it.minute,
                    it.player,
                    it.team,
                    it.teamId
                )
            },
            from.substitutions?.map {
                Substitution(
                    it.minute,
                    it.playerOff,
                    it.playerOn,
                    it.reason,
                    it.team,
                    it.teamId
                )
            },
            from.awayTeam,
            from.homeTeam
        ),
        intent = ChatMessageIntent.RECEIVED_LIVE_MATCH_UPDATE
    )
}