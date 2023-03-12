package com.example.onlineshopsatria.model.accounts.entities

import com.example.onlineshopsatria.model.EmailMismatchException
import com.example.onlineshopsatria.model.EmptyFieldException
import com.example.onlineshopsatria.model.Field

data class SignUpData(
    val firstName: String,
    val lastName: String,
    val email: String,
) {
    fun validate() {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (firstName.isBlank()) throw EmptyFieldException(Field.Firstname)
        if (lastName.isBlank()) throw EmptyFieldException(Field.Lastname)
        if (!email.contains("@mail.ru")) throw EmailMismatchException()
    }
}