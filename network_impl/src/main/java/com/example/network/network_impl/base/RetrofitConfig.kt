package com.example.network.network_impl.base

import com.squareup.moshi.Moshi
import retrofit2.Retrofit

class RetrofitConfig(
    val retrofit: Retrofit,
    val moshi: Moshi
)