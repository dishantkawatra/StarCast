package com.example.starcast.apirepo


import com.example.starcast.fragment.homescreen.CharacterDetailResponse
import kotlinx.coroutines.Deferred


interface APIRepository {
    fun searchPeople(people: HashMap<String, String>): Deferred<retrofit2.Response<ServerResponse<List<CharacterDetailResponse>>>>
    fun searchFilmData(filmUrl: String): Deferred<retrofit2.Response<ServerResponse<CharacterDetailResponse>>>

}
