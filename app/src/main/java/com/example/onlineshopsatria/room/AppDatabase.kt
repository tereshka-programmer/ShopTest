package com.example.onlineshopsatria.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlineshopsatria.model.accounts.room.AccountsDao
import com.example.onlineshopsatria.model.accounts.room.entities.AccountDbEntity

@Database(
    version = 1,
    entities = [
        AccountDbEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

}