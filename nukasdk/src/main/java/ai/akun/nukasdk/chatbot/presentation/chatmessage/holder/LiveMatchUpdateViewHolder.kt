package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.shared.ImageLoader
import android.annotation.SuppressLint
import android.view.View
import kotlinx.android.synthetic.main.item_row_match_update.view.*

class LiveMatchUpdateViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    override fun bind(chatMessage: ChatMessage) {
        when {
            chatMessage.liveMatchUpdate?.bookings != null -> {
                val booking = chatMessage.liveMatchUpdate.bookings.first()
                loadData(
                    booking.team,
                    booking.minute,
                    R.drawable.ic_red_card,
                    booking.player,
                    chatMessage.liveMatchUpdate.homeTeam!!,
                    chatMessage.liveMatchUpdate.awayTeam!!
                )
            }
            chatMessage.liveMatchUpdate?.scorers != null -> {
                val scorer = chatMessage.liveMatchUpdate.scorers.first()
                loadData(
                    scorer.team,
                    scorer.minute,
                    R.drawable.ic_ball,
                    scorer.player,
                    chatMessage.liveMatchUpdate.homeTeam!!,
                    chatMessage.liveMatchUpdate.awayTeam!!
                )
            }
            chatMessage.liveMatchUpdate?.substitutions != null -> {
                val substitution = chatMessage.liveMatchUpdate.substitutions.first()
                loadData(
                    substitution.team,
                    substitution.minute,
                    R.drawable.ic_substitution,
                    itemView.context.getString(R.string.substitution_description, substitution.playerOn, substitution.playerOff),
                    chatMessage.liveMatchUpdate.homeTeam!!,
                    chatMessage.liveMatchUpdate.awayTeam!!
                )
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadData(
        team: String,
        minute: Int,
        iconResource: Int,
        text: String,
        homeTeam: String,
        awayTeam: String
    ) {
        itemView.team.text = team
        itemView.minute.text = "$minute'"
        ImageLoader.load(iconResource, itemView.updateIcon)
        itemView.content.text = text
        itemView.homeTeam.text = homeTeam
        itemView.awayTeam.text = awayTeam
    }

}