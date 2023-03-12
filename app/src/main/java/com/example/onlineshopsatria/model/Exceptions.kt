package com.example.onlineshopsatria.model

open class AppException : RuntimeException()

class EmptyFieldException(
    val field: Field
) : AppException()

class EmailMismatchException : AppException()

class PasswordMismatchException : AppException()

class AccountAlreadyExistsException : AppException()

class AuthException : AppException()

class StorageException: AppException()