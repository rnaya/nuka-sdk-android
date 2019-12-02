package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.Match
import ai.akun.nukasdk.chatbot.presentation.shared.ImageLoader
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_match.view.*

class MatchesAdapter :
    RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {

    private var matches: MutableList<Match> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val inflatedView = parent.inflate(R.layout.item_row_match)
        return MatchViewHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(viewHolder: MatchViewHolder, position: Int) {
        viewHolder.bind(matches[position])
    }

    fun load(matches: List<Match>) {
        this.matches = matches.toMutableList()
        notifyDataSetChanged()
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(match: Match) {
            itemView.title.text = "${match.homeTeam.name} - ${match.awayTeam.name}"
            itemView.subtitle.text = match.competition
            ImageLoader.load(getTeamLogoUrl(match.homeTeam.identifier), itemView.homeTeamImage, R.drawable.ic_shield)
            itemView.homeTeamShortName.text = match.homeTeam.shortName
            itemView.scheduledDate.text = match.scheduledDate
            ImageLoader.load(getTeamLogoUrl(match.awayTeam.identifier), itemView.awayTeamImage, R.drawable.ic_shield)
            itemView.awayTeamShortName.text = match.awayTeam.shortName
        }

        private fun getTeamLogoUrl(identifier: String): String{
            return "https://nuka.raisting.co/img/Team/$identifier.png"
        }
    }

}