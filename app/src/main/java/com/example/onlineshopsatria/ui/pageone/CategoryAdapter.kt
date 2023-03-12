package com.example.onlineshopsatria.ui.pageone

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.network.network_impl.products.entities.FlashSaleItem
import com.example.onlineshopsatria.R
import com.example.onlineshopsatria.databinding.ItemCategoryBinding

class CategoryAdapter(
    context: Context
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    var categoryItemsList: List<CategoryItem> = categoryList
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)

        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int = categoryItemsList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryItem = categoryItemsList[position]
        holder.itemView.tag = categoryItem

        with(holder.binding) {
            tvNameOfCategory.text = categoryItem.name
            imageCategory.setImageDrawable(ContextCompat.getDrawable(holder.itemView.context, categoryItem.drawable))
        }
    }

    class CategoryViewHolder(
        val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val categoryList = listOf<CategoryItem>(
            CategoryItem("Phones", R.drawable.ic_category_phone),
            CategoryItem("Headphones", R.drawable.ic_category_headphones),
            CategoryItem("Phones", R.drawable.ic_games),
            CategoryItem("Cars", R.drawable.ic_category_cars),
            CategoryItem("Furniture", R.drawable.ic_category_furniture),
            CategoryItem("Kids", R.drawable.ic_category_kids)
        )
    }

    data class CategoryItem(
        val name: String,
        val drawable: Int
    )
}