package com.example.starcast

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.example.starcast.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class App : Application() {
    init {
        instance = this
    }
    companion object {
        private var instance: App? = null
    }
    override fun onCreate() {
        super.onCreate()
        startModule()
    }
    private fun startModule() {
        startKoin {
            androidContext(this@App)
            modules(Modules.getAll())
        }
    }
}