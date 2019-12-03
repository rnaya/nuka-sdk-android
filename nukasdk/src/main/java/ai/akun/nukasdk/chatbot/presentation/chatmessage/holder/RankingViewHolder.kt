package ai.akun.nukasdk.chatbot.presentation.chatmessage.holder

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessage
import ai.akun.nukasdk.chatbot.presentation.main.ChatMessageIntent
import ai.akun.nukasdk.chatbot.presentation.shared.ImageLoader
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.item_row_ranking.view.*
import kotlinx.android.synthetic.main.item_row_received_ranking_chat_message.view.*

class RankingViewHolder(itemView: View) : ChatMessageViewHolder(itemView) {

    @SuppressLint("InflateParams")
    override fun bind(chatMessage: ChatMessage) {
        val inflater = itemView.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        chatMessage.webhookPayload?.rankings?.forEach {
            val rankingItem = inflater.inflate(R.layout.item_row_ranking, null)
            rankingItem.position.text = it.position.toString()
            ImageLoader.load(getTeamShieldUrl(it.teamUid), rankingItem.shield, R.drawable.ic_shield)
            rankingItem.teamName.text = it.teamName
            rankingItem.points.text = it.points.toString()
            rankingItem.wonGames.text = it.won.toString()
            rankingItem.drawGames.text = it.drawn.toString()
            rankingItem.lostGames.text = it.lost.toString()
            itemView.rankingContainer.addView(rankingItem)
        }
    }

    private fun getTeamShieldUrl(identifier: String): String{
        return "https://nuka.raisting.co/img/Team/$identifier.png"
    }
}