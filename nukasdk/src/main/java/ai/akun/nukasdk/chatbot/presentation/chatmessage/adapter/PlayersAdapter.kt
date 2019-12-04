package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.Player
import ai.akun.nukasdk.chatbot.presentation.shared.ImageLoader
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_player.view.*
import org.joda.time.DateTime
import org.joda.time.Years
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class PlayersAdapter :
    RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    private var players: MutableList<Player> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val inflatedView = parent.inflate(R.layout.item_row_player)
        return PlayerViewHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(viewHolder: PlayerViewHolder, position: Int) {
        viewHolder.bind(players[position])
    }

    fun load(players: List<Player>) {
        this.players = players.toMutableList()
        notifyDataSetChanged()
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(player: Player) {
            itemView.position.text = player.position
            ImageLoader.load(getPlayerImageUrl(player.identifier), itemView.playerImage, R.drawable.ic_player)
            itemView.playerName.text = player.name
            itemView.playerAge.text = getAgeFromBirthDate(player.birthDate) ?: "-"
            itemView.playerHeight.text = "-"
            player.height?.let {
                itemView.playerHeight.text = it.toString()
            }
            itemView.playerWeight.text = "-"
            player.weight?.let {
                itemView.playerWeight.text = it.toString()
            }
            itemView.playerContract.text = getYearFromDate(player.joinDate) ?: "-"
        }

        private fun getPlayerImageUrl(identifier: String): String{
            return "https://nuka.raisting.co/img/Player/$identifier.png"
        }

        private fun getAgeFromBirthDate(birthDateString: String?): String? {
            if(birthDateString.isNullOrBlank())
                return null

            val birthDate = getDateFromString(birthDateString)
            val today = DateTime.now()

            return Years.yearsBetween(birthDate, today).years.toString()
        }

        private fun getYearFromDate(dateString: String?): String? {
            if(dateString.isNullOrBlank())
                return null

            return getDateFromString(dateString).year.toString()
        }

        private fun getDateFromString(dateString: String): DateTime {
            val formatter: DateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy HH:mm")
            return formatter.parseDateTime(dateString)
        }
    }

}