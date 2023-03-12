package com.example.onlineshopsatria.model.accounts.room.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class AccountSignInTuple(
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "password") val password: String
)

data class AccountUpdateImagePathTuple(
    @ColumnInfo(name = "id") @PrimaryKey val id: Long,
    @ColumnInfo(name = "path") val path: String
)