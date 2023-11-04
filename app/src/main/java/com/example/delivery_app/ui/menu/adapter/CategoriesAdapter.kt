package com.example.delivery_app.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.delivery_app.R
import com.example.delivery_app.databinding.CategoryItemBinding

class CategoriesAdapter(
    private val onItemClicked: (categoryName: String) -> Unit,
    private val data: MutableMap<String, Boolean>,
    private var selectedPosition: Int
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    inner class CategoriesViewHolder(
        itemView: View,
        private val onItemClicked: (categoryName: String) -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Pair<String, Boolean>, position: Int) {
            val binding = CategoryItemBinding.bind(itemView)
            itemView.setOnClickListener {
                onItemClicked(category.first)
                notifyItemChanged(selectedPosition)
                notifyItemChanged(position)
                selectedPosition = position
            }
            binding.apply {
                binding.tvCategoryName.text = category.first

                if (category.second) {
                    clCategory.setBackgroundColor(context.getColor(R.color.pink_background))
                    tvCategoryName.setTextColor(context.getColor(R.color.pink))
                } else {
                    clCategory.setBackgroundColor(context.getColor(R.color.white))
                    tvCategoryName.setTextColor(context.getColor(R.color.text_not_selected))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder =
        CategoriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item, parent, false), onItemClicked, parent.context
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) =
        holder.bind(
            Pair(data.keys.toList()[position], data[data.keys.toList()[position]]!!),
            position
        )
}