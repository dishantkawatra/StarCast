package com.example.starcast.utils
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import com.example.starcast.apirepo.APIRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel(private val app: Application, val apiRepo: APIRepository) : AndroidViewModel(app), CoroutineScope {


    private val failedJobIdList = mutableSetOf<String>()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCleared() {
        super.onCleared()
        failedJobIdList.clear()
        job.cancel()
    }


















}