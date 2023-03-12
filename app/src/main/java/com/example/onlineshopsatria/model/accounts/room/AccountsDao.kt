package com.example.onlineshopsatria.model.accounts.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.onlineshopsatria.model.accounts.room.entities.AccountDbEntity
import com.example.onlineshopsatria.model.accounts.room.entities.AccountSignInTuple
import com.example.onlineshopsatria.model.accounts.room.entities.AccountUpdateImagePathTuple
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountsDao {

    @Query("SELECT * FROM account")
    suspend fun getAll(): List<AccountDbEntity?>

    @Query("SELECT * FROM account WHERE first_name = :firstName")
    suspend fun findByFirsNameAllEntity(firstName: String): AccountDbEntity?

    @Query("SELECT id, password FROM account WHERE first_name = :firstName")
    suspend fun findByFirsName(firstName: String): AccountSignInTuple?

    @Update(entity = AccountDbEntity::class)
    suspend fun updateUserImagePath(path: AccountUpdateImagePathTuple)

    @Insert(entity = AccountDbEntity::class)
    suspend fun createAccount(accountDbEntity: AccountDbEntity)

    @Query("SELECT * FROM account WHERE id = :accountId")
    fun getById(accountId: Long): Flow<AccountDbEntity?>

}