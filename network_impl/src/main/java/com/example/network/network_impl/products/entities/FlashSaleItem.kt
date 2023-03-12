package com.example.network.network_impl.products.entities

import com.squareup.moshi.Json


data class FlashSales(
    @field:Json(name = "flash_sale") val flashSalesItems: List<FlashSaleItem>
)


data class FlashSaleItem(
    val category: String,
    val discount: Int,
    val image_url: String,
    val name: String,
    val price: Double
)