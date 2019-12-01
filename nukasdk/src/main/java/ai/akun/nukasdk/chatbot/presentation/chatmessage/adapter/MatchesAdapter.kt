package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.Card
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_match.view.*

class MatchesAdapter :
    RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>() {

    private var matches: MutableList<Card> = mutableListOf()

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

    fun load(matches: List<Card>) {
        this.matches = matches.toMutableList()
        notifyDataSetChanged()
    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card) {
            itemView.title.text = card.title
            itemView.subtitle.text = card.subtitle
        }
    }

}