package com.example.starcast.network
sealed class NetworkResult<T> {
    data class Success<T> (val body: T, val code: Int) : NetworkResult<T>()
    data class  Failure<T>(val errorMessage: String, val code: Int) : NetworkResult<T>()
}