package com.example.onlineshopsatria.model.settings

interface AppSettings {


    fun getCurrentAccountId(): Long

    fun setCurrentAccountId(accountId: Long)

    companion object {
        /**
         * Indicates that there is no logged-in user.
         */
        const val NO_ACCOUNT_ID = -1L
    }

}