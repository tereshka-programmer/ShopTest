package com.example.onlineshopsatria.ui.pageone

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.network.network_impl.products.entities.FlashSaleItem
import com.example.network.network_impl.products.entities.LatestItem
import com.example.onlineshopsatria.databinding.ItemFlashSaleBinding

class FlashSaleAdapter(

) : RecyclerView.Adapter<FlashSaleAdapter.FlashSaleViewHolder>() {

    var flashSalesList: List<FlashSaleItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashSaleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFlashSaleBinding.inflate(layoutInflater, parent, false)

        return FlashSaleViewHolder(binding)
    }

    override fun getItemCount(): Int = flashSalesList.size

    override fun onBindViewHolder(holder: FlashSaleViewHolder, position: Int) {
        val flashSaleItem = flashSalesList[position]
        holder.itemView.tag = flashSaleItem

        with(holder.binding) {
            tvNameOfItem.text = flashSaleItem.name
            tvType.text = flashSaleItem.category
            tvPriceOfItem.text = "${'$'}${flashSaleItem.price}"
            tvDiscont.text = "${flashSaleItem.discount} % off"

            if (flashSaleItem.image_url.isNotBlank()) {
                Glide.with(mainnn.context)
                    .load(flashSaleItem.image_url)
                    .centerCrop()
                    .into(imageBack)
            }

        }
    }

    class FlashSaleViewHolder(
        val binding: ItemFlashSaleBinding
    ) : RecyclerView.ViewHolder(binding.root)
}