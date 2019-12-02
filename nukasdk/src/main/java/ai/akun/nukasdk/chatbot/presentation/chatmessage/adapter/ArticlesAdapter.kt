package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.Article
import ai.akun.nukasdk.chatbot.presentation.shared.ImageLoader
import ai.akun.nukasdk.chatbot.presentation.shared.UrlNavigator
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_article.view.*

class ArticlesAdapter :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var articles: MutableList<Article> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflatedView = parent.inflate(R.layout.item_row_article)
        return ArticleViewHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(viewHolder: ArticleViewHolder, position: Int) {
        viewHolder.bind(articles[position])
    }

    fun load(article: List<Article>) {
        this.articles = article.toMutableList()
        notifyDataSetChanged()
    }

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) {
            itemView.category.text = article.category
            ImageLoader.load(article.image, itemView.articleImage)
            itemView.articleTitle.text = article.title
            itemView.articleSubtitle.text = article.subtitle

            if(article.category == "Video") {
                itemView.playButton.visibility = View.VISIBLE
            }

            itemView.setOnClickListener {
                UrlNavigator.navigate(article.website, itemView.context)
            }
        }

    }

}