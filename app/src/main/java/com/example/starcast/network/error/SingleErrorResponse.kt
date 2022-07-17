package com.example.starcast.network.error
import com.google.gson.annotations.SerializedName

data class SingleErrorResponse(
    @SerializedName("error") val error: String,
    @SerializedName("errorMessage") val errorMessage: String
)