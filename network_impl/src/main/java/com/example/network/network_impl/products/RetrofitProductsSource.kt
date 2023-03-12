package com.example.network.network_impl.products

import android.util.Log
import com.example.network.network_impl.base.BaseRetrofitSource
import com.example.network.network_impl.base.RetrofitConfig
import com.example.network.network_impl.products.entities.FlashSaleItem
import com.example.network.network_impl.products.entities.LatestItem
import retrofit2.create

class RetrofitProductsSource(
    config: RetrofitConfig
) : BaseRetrofitSource(config), ProductsSource{

    private val productsApi = retrofit.create(ProductsApi::class.java)

    override suspend fun getAllFlashSaleItems(): List<FlashSaleItem> = wrapRetrofitExceptions {
        productsApi.getFlashSalesItems().flashSalesItems
    }

    override suspend fun getAllLatestItems(): List<LatestItem> = wrapRetrofitExceptions {
        productsApi.getLatestItems().latestItems
    }


}