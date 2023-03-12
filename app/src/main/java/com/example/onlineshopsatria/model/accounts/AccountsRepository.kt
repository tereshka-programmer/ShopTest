package com.example.onlineshopsatria.model.accounts

import com.example.onlineshopsatria.model.accounts.entities.Account
import com.example.onlineshopsatria.model.accounts.entities.SignUpData
import kotlinx.coroutines.flow.Flow

interface AccountsRepository {

    suspend fun logIn(firstName: String, password: String)

    suspend fun signUp(signUpData: SignUpData)

    suspend fun logout()

    suspend fun getAccount(): Flow<Account?>

    suspend fun updateUserImagePath(newPath: String)

}