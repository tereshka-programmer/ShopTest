package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.onlineshopsatria.model.accounts.room.AccountsDao
import com.example.onlineshopsatria.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideAccountsDao(appDatabase: AppDatabase): AccountsDao {
        return appDatabase.getAccountsDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context)
    : AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "database.db")
            .build()
    }
}