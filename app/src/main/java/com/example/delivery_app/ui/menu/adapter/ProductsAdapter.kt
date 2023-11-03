package com.example.delivery_app.ui.menu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_app.R
import com.example.delivery_app.data.model.Product
import com.example.delivery_app.databinding.ProductListItemBinding
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val onItemClicked: (product: Product) -> Unit,
    private val data: MutableList<Product>
) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(
        itemView: View,
        private val onItemClicked: (product: Product) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(product: Product) {
            val binding = ProductListItemBinding.bind(itemView)
            itemView.setOnClickListener { onItemClicked(product) }
            binding.apply {
                Picasso.get().load(product.images[0]).fit().into(ivRegionImage)
                tvName.text = product.title
                tvDesc.text = product.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder =
        ProductsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_list_item, parent, false), onItemClicked
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) =
        holder.bind(data[position])

    fun clear() = data.clear()

    fun addData(products: List<Product>) = this.data.addAll(products)
}