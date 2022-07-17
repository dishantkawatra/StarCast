package com.example.starcast.network.error
import com.google.gson.annotations.SerializedName

data class DynamicErrorResponse(
    @SerializedName("errors") val errors: Map<String, List<String>>
)