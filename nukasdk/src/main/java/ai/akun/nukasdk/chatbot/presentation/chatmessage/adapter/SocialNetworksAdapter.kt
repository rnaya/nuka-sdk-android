package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.Card
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_social_network.view.*

class SocialNetworksAdapter :
    RecyclerView.Adapter<SocialNetworksAdapter.SocialNetworkViewHolder>() {

    private var socialNetworks: MutableList<Card> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialNetworkViewHolder {
        val inflatedView = parent.inflate(R.layout.item_row_social_network)
        return SocialNetworkViewHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = socialNetworks.size

    override fun onBindViewHolder(viewHolder: SocialNetworkViewHolder, position: Int) {
        viewHolder.bind(socialNetworks[position])
    }

    fun load(socialNetworks: List<Card>) {
        this.socialNetworks = socialNetworks.toMutableList()
        notifyDataSetChanged()
    }

    class SocialNetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card) {
            itemView.description.text = card.title
        }
    }

}