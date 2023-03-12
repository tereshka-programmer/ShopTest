package com.example.onlineshopsatria.model.accounts.entities

data class Account(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val imagePath: String = ""
)