package com.example.onlineshopsatria.model.accounts.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.onlineshopsatria.model.accounts.entities.Account
import com.example.onlineshopsatria.model.accounts.entities.SignUpData


@Entity(
    tableName = "account",
    indices = [
        Index("email", unique = true)
    ]
)
data class AccountDbEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "email", collate = ColumnInfo.NOCASE) val email: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "path") val path: String,
    @ColumnInfo(name = "password")val password: String
) {

    fun toAccount(): Account = Account(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        imagePath = path
    )

    companion object {
        fun fromSignUpData(signUpData: SignUpData) = AccountDbEntity(
            id = 0,
            email = signUpData.email,
            firstName = signUpData.firstName,
            lastName = signUpData.lastName,
            path = "",
            password = "12345"
        )
    }
}