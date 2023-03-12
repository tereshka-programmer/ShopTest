package com.example.network.network_impl.products.entities

import com.squareup.moshi.Json

data class Latest(
    @field:Json(name = "latest") val latestItems: List<LatestItem>
)

data class LatestItem(
    val category: String,
    val image_url: String,
    val name: String,
    val price: Int
)