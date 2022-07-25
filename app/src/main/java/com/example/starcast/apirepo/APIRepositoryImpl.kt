package com.example.starcast.apirepo


import com.example.starcast.fragment.homescreen.CharacterDetailResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response

class APIRepositoryImpl(private val api: API) : APIRepository {
    override fun searchPeople(people: HashMap<String, String>): Deferred<Response<ServerResponse<List<CharacterDetailResponse>>>> {
        return api.searchPeople(people)
    }

    override fun searchFilmData(filmUrl: String): Deferred<Response<ServerResponse<CharacterDetailResponse>>> {
        return api.searchFilmData(filmUrl)
    }


}

