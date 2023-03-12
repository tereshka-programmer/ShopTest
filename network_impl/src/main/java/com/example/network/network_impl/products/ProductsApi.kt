package com.example.network.network_impl.products

import com.example.network.network_impl.products.entities.FlashSales
import com.example.network.network_impl.products.entities.Latest
import retrofit2.http.GET

interface ProductsApi {

    @GET("a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getFlashSalesItems(): FlashSales

    @GET("cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatestItems(): Latest

}