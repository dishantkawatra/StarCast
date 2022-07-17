package com.example.starcast.apirepo

import com.example.starcast.fragment.homescreen.CharacterDetailResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface API {
    @GET("/api/people/")
    fun searchPeople(@QueryMap params: HashMap<String, String>): Deferred<Response<ServerResponse<List<CharacterDetailResponse>>>>


}

