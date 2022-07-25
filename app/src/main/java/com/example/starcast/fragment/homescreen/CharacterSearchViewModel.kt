package com.example.starcast.fragment.homescreen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.starcast.utils.BaseViewModel
import com.example.starcast.apirepo.APIRepository
import com.example.starcast.apirepo.awaitAndGet
import com.example.starcast.network.NetworkResult
import com.example.starcast.state.Event
import com.example.starcast.state.ResultState
import kotlinx.coroutines.launch

class CharacterSearchViewModel(app: Application, apiRepo: APIRepository) : BaseViewModel(app, apiRepo) {

    fun getDashboardCountList(people: HashMap<String, String>) {
        launch {
            val result = apiRepo.searchPeople(people).awaitAndGet()
            characterSearchLiveData.postValue(Event(when (result) {
                is NetworkResult.Failure -> {
                    ResultState.Failure(result.errorMessage, result.code)
                }
                is NetworkResult.Success -> result.body.run {
                    ResultState.Success(result.body.data)
                }
            }))
        }

    }


    fun getFilmDataApi(filmUrl: String) {
        launch {
            val result = apiRepo.searchFilmData(filmUrl).awaitAndGet()
            getCharacterFilmDetail.postValue(Event(when (result) {
                is NetworkResult.Failure -> {
                    ResultState.Failure(result.errorMessage, result.code)
                }
                is NetworkResult.Success -> result.body.run {
                    ResultState.Success(result.body,result.body.opening_crawl)
                }
            }))
        }

    }

    private lateinit var characterSearchLiveData: MutableLiveData<Event<ResultState>>
    fun characterSearchApi(): LiveData<Event<ResultState>> {
        if (!::characterSearchLiveData.isInitialized) {
            characterSearchLiveData = MutableLiveData()
        }
        return characterSearchLiveData
    }


    private lateinit var getCharacterFilmDetail: MutableLiveData<Event<ResultState>>
    fun characterFilmDetail(): LiveData<Event<ResultState>> {
        if (!::getCharacterFilmDetail.isInitialized) {
            getCharacterFilmDetail = MutableLiveData()
        }
        return getCharacterFilmDetail
    }
}