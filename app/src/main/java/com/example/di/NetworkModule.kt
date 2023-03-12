package com.example.di

import com.example.network.network_impl.SourceProviderHolder
import com.example.network.network_impl.products.ProductsSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideProductSource(): ProductsSource {
        return SourceProviderHolder.sourcesProvider.getProductsSource()
    }

}