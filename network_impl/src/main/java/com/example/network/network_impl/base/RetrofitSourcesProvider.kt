package com.example.network.network_impl.base

import com.example.network.network_impl.products.ProductsSource
import com.example.network.network_impl.products.RetrofitProductsSource

class RetrofitSourcesProvider(
    private val config: RetrofitConfig
) {

    fun getProductsSource(): ProductsSource {
        return RetrofitProductsSource(config)
    }

}