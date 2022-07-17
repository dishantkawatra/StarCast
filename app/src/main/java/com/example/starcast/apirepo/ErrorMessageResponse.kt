package com.example.starcast.apirepo

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data  class ErrorMessageResponse(
    @SerializedName("com.example.starcast.network.error.getErrorCode")
    var errorCode: Int = 0,
    @SerializedName("errorMessage")
    var errorMessage: String = "",
    @SerializedName("status")
    var status: Boolean? = false
) : Parcelable