package com.example.starcast.network.error
import com.google.gson.annotations.SerializedName

data class JsonApiErrorResponse(
        @SerializedName("errors") val errorList: List<Error>
) {
    data class Error(
        @SerializedName("status") val status: Int,
        @SerializedName("code") val code: Int,
        @SerializedName("title") val title: String,
        @SerializedName("message") val message: String,
        @SerializedName("detail") val detail: String
    )
}