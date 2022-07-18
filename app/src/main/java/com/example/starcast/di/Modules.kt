package com.example.starcast.di
import com.example.starcast.fragment.homescreen.CharacterSearchViewModel
import com.example.starcast.apirepo.API
import com.example.starcast.apirepo.APIRepository
import com.example.starcast.apirepo.APIRepositoryImpl
import com.example.starcast.network.NetworkBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {


    private val viewModelModules = module {
        viewModel { CharacterSearchViewModel(get(), get()) }
    }

    private val networkModules = module {
        single { NetworkBuilder.create(API::class.java) }
    }

    private val repoModules = module {
        single<APIRepository> { APIRepositoryImpl(get()) }
    }

    fun getAll() = listOf(viewModelModules, networkModules, repoModules)


}