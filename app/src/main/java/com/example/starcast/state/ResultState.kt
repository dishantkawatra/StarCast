package com.example.starcast.state

sealed class ResultState {
    data class Success<V>(val data: V?, val message: String?="") : ResultState()
    data class Failure(val errorMessage: String, val errorCode: Int) : ResultState()
}