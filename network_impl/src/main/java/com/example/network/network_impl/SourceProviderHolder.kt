package com.example.network.network_impl

import com.example.network.network_impl.base.RetrofitConfig
import com.example.network.network_impl.base.RetrofitSourcesProvider
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object SourceProviderHolder {

    private const val BASE_URL = "https://run.mocky.io/v3/"

    val sourcesProvider: RetrofitSourcesProvider by lazy {
        val moshi = Moshi.Builder().build()
        val config = RetrofitConfig(
            retrofit = createRetrofit(moshi),
            moshi = moshi
        )
        RetrofitSourcesProvider(config)
    }

    private fun createRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(createLoggingInterceptor())
            .build()
    }

    private fun createLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }



}