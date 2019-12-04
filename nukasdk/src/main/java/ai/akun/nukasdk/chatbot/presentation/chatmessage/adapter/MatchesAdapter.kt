package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.Match
import ai.akun.nukasdk.chatbot.presentation.shared.ImageLoader
import ai.akun.nukasdk.chatbot.presentation.shared.UrlNavigator
import ai.akun.nukasdk.chatbot.presentation.shared.hasValidLocation
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_match.view.*
import timber.log.Timber


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
            ImageLoader.load(getTeamShieldUrl(match.homeTeam.identifier), itemView.homeTeamShield, R.drawable.ic_shield)
            itemView.homeTeamShortName.text = match.homeTeam.shortName
            itemView.scheduledDate.text = match.scheduledDate
            ImageLoader.load(getTeamShieldUrl(match.awayTeam.identifier), itemView.awayTeamShield, R.drawable.ic_shield)
            itemView.awayTeamShortName.text = match.awayTeam.shortName


            if(match.venue.hasValidLocation()) {
                itemView.directions.visibility = View.VISIBLE
                itemView.directions.setOnClickListener {
                    try {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?daddr=${match.venue.latitude},${match.venue.longitude}")
                        )
                        startActivity(itemView.context, intent, null)
                    } catch (ex: Exception) {
                        Timber.e(ex, "Error while trying to open maps for venue directions")
                        Toast.makeText(itemView.context, itemView.context.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            match.tickets?.let {url ->
                itemView.tickets.visibility = View.VISIBLE
                itemView.tickets.setOnClickListener {
                    UrlNavigator.navigate(url, itemView.context)
                }
            }

        }

        private fun getTeamShieldUrl(identifier: String): String{
            return "https://nuka.raisting.co/img/Team/$identifier.png"
        }
    }

}