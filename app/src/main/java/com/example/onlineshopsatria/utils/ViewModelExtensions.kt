package com.example.onlineshopsatria.utils

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.requireValue(): T {
    return this.value ?: throw IllegalStateException("Value is empty")
}