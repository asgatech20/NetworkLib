package com.asga.network.data

/* Created by ibrahim.ali on 9/28/2021 */
sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}