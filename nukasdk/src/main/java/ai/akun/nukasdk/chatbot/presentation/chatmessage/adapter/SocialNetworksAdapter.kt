package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.Card
import ai.akun.nukasdk.chatbot.presentation.shared.ImageLoader
import ai.akun.nukasdk.chatbot.presentation.shared.UrlNavigator
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
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
            itemView.title.text = card.title
            itemView.socialNetwork.text = card.subtitle
            ImageLoader.load(card.imageUri, itemView.socialNetworkIcon)

            itemView.open.setOnClickListener {
                UrlNavigator.navigate(card.buttons?.first()?.postback.toString(), itemView.context)
            }
        }
    }

}