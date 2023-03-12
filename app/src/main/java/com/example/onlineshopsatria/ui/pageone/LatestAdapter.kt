package com.example.onlineshopsatria.ui.pageone

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.network.network_impl.products.entities.LatestItem
import com.example.onlineshopsatria.databinding.ItemLatestBinding

class LatestAdapter(

) : RecyclerView.Adapter<LatestAdapter.LatestViewHolder>() {

    var listOfLatest: List<LatestItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLatestBinding.inflate(inflater, parent, false)

        return LatestViewHolder(binding)
    }

    override fun getItemCount(): Int = listOfLatest.size

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        val latestItem = listOfLatest[position]
        holder.itemView.tag = latestItem

        with(holder.binding) {
            tvType.text = latestItem.category
            tvNameOfItem.text = latestItem.name
            tvPriceOfItem.text = "${'$'}${latestItem.price}"

            if (latestItem.image_url.isNotBlank()) {
                Glide.with(mainLinear.context)
                    .load(latestItem.image_url)
                    .centerCrop()
                    .into(imageBack)

            }
        }
    }

    class LatestViewHolder(
        val binding: ItemLatestBinding
    ) : RecyclerView.ViewHolder(binding.root)
}




