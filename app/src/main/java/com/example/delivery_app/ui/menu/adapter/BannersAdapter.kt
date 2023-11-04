package com.example.delivery_app.ui.menu.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_app.R
import com.example.delivery_app.databinding.BannerItemBinding
import com.squareup.picasso.Picasso

class BannersAdapter(
    private val onItemClicked: (url: String) -> Unit,
    private val data: MutableList<Int>,
) : RecyclerView.Adapter<BannersAdapter.BannersViewHolder>() {

    inner class BannersViewHolder(
        itemView: View,
        private val onItemClicked: (url: String) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(banner: Int) {
            val binding = BannerItemBinding.bind(itemView)
            itemView.setOnClickListener {
                onItemClicked("")

            }
            binding.apply {
                Picasso.get().load(banner).fit().into(ivBanner)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder =
        BannersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.banner_item, parent, false), onItemClicked
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        holder.bind(data[position])
    }
}