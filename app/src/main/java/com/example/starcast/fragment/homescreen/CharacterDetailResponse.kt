package com.example.starcast.fragment.homescreen

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CharacterDetailResponse(val name: String, val height:String, val birth_year:String, val hair_color:String):
 Parcelable