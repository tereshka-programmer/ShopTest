package com.example.onlineshopsatria.model.accounts.room

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import android.util.Log
import com.example.onlineshopsatria.model.AccountAlreadyExistsException
import com.example.onlineshopsatria.model.AuthException
import com.example.onlineshopsatria.model.EmptyFieldException
import com.example.onlineshopsatria.model.Field
import com.example.onlineshopsatria.model.accounts.AccountsRepository
import com.example.onlineshopsatria.model.accounts.entities.Account
import com.example.onlineshopsatria.model.accounts.entities.SignUpData
import com.example.onlineshopsatria.model.accounts.room.entities.AccountDbEntity
import com.example.onlineshopsatria.model.accounts.room.entities.AccountUpdateImagePathTuple
import com.example.onlineshopsatria.model.settings.AppSettings
import com.example.onlineshopsatria.room.wrapSQLiteException
import com.example.onlineshopsatria.utils.AsyncLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomAccountsRepository @Inject constructor(
    private val accountsDao: AccountsDao,
    private val appSettings: AppSettings
) : AccountsRepository {

    private val currentAccountIdFlow = AsyncLoader {
        MutableStateFlow(AccountId(appSettings.getCurrentAccountId()))
    }

    override suspend fun logIn(firstName: String, password: String) = wrapSQLiteException(Dispatchers.IO) {
        Log.d("PASSWORDDDDDD", "LOGIN FUNC")
        if (firstName.isBlank()) throw EmptyFieldException(Field.Email)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)

        val accountId = findAccountIdByFirstnameAndPassword(firstName, password)
        appSettings.setCurrentAccountId(accountId)
        currentAccountIdFlow.get().value = AccountId(accountId)

        return@wrapSQLiteException
    }

    override suspend fun signUp(signUpData: SignUpData) = wrapSQLiteException(Dispatchers.IO) {
        signUpData.validate()
        createAccount(signUpData)
    }

    override suspend fun logout() {
        appSettings.setCurrentAccountId(AppSettings.NO_ACCOUNT_ID)
        currentAccountIdFlow.get().value = AccountId(AppSettings.NO_ACCOUNT_ID)
    }

    override suspend fun getAccount(): Flow<Account?> {
        return currentAccountIdFlow.get()
            .flatMapLatest { accountId ->
                if (accountId.value == AppSettings.NO_ACCOUNT_ID) {
                    flowOf(null)
                } else {
                    getAccountById(accountId.value)
                }
            }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateUserImagePath(newPath: String) = wrapSQLiteException(Dispatchers.IO) {
        val accountId = appSettings.getCurrentAccountId()
        updateImagePathForAccountId(accountId, newPath)

        return@wrapSQLiteException
    }

    private suspend fun findAccountIdByFirstnameAndPassword(firstName: String, password: String): Long {
        val tuple = accountsDao.findByFirsName(firstName) ?: throw AuthException()

        Log.d("PASSWORDDDDDD", tuple.password)
        if (tuple.password != password) throw AuthException()
        return tuple.id
    }

    private suspend fun createAccount(signUpData: SignUpData) {
        try {
            val entity = AccountDbEntity.fromSignUpData(signUpData)
            accountsDao.createAccount(entity)
        } catch (e: SQLiteConstraintException) {
            val appException = AccountAlreadyExistsException()
            appException.initCause(e)
            throw appException
        }
    }

    private fun getAccountById(accountId: Long): Flow<Account?> {
        return accountsDao.getById(accountId).map { it?.toAccount() }
    }

    private suspend fun updateImagePathForAccountId(accountId: Long, newPath: String) {
        accountsDao.updateUserImagePath(AccountUpdateImagePathTuple(accountId, newPath))
    }

    private class AccountId(val value: Long)
}