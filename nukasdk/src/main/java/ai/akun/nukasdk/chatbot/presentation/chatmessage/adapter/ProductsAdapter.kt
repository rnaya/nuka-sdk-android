package ai.akun.nukasdk.chatbot.presentation.chatmessage.adapter

import ai.akun.nukasdk.R
import ai.akun.nukasdk.chatbot.presentation.main.Product
import ai.akun.nukasdk.chatbot.presentation.shared.ImageLoader
import ai.akun.nukasdk.chatbot.presentation.shared.UrlNavigator
import ai.akun.nukasdk.chatbot.presentation.shared.inflate
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_product.view.*

class ProductsAdapter :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var products: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflatedView = parent.inflate(R.layout.item_row_product)
        return ProductViewHolder(
            inflatedView
        )
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ProductViewHolder, position: Int) {
        viewHolder.bind(products[position])
    }

    fun load(product: List<Product>) {
        this.products = product.toMutableList()
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(product: Product) {
            itemView.category.text = product.category
            ImageLoader.load(product.image, itemView.productImage)
            itemView.productDescription.text = product.title
            itemView.productPrice.text = "$" + String.format("%.2f",product.price)

            itemView.buy.setOnClickListener {
                UrlNavigator.navigate(product.website, itemView.context)
            }
        }

    }

}