package com.example.network.network_impl.products

import com.example.network.network_impl.products.entities.FlashSaleItem
import com.example.network.network_impl.products.entities.LatestItem

interface ProductsSource {

    suspend fun getAllFlashSaleItems(): List<FlashSaleItem>

    suspend fun getAllLatestItems(): List<LatestItem>

}