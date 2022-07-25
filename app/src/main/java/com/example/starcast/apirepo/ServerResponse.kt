package com.example.starcast.apirepo
import com.google.gson.annotations.SerializedName

data class ServerResponse<T>(

    @SerializedName("count") val count: Int,
    @SerializedName("opening_crawl") val opening_crawl: String,
    @SerializedName("results") val data: T
)

