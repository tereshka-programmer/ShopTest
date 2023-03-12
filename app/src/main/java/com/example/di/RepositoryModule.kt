package com.example.di

import com.example.onlineshopsatria.model.accounts.AccountsRepository
import com.example.onlineshopsatria.model.accounts.room.RoomAccountsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAccountsRepository(
        accountsRepository: RoomAccountsRepository
    ) : AccountsRepository

}