package com.example.starcast.network.error
import com.google.gson.annotations.SerializedName

data class
DefaultErrorResponse(
    @SerializedName("type") val type: String?,
    @SerializedName("message") val message: String
)