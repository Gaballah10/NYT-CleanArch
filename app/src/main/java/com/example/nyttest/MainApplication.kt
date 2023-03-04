package com.example.nyttest

import android.app.Application
import com.example.nyttest.di.appModule
import com.example.nyttest.di.dbRepoModules
import com.example.nyttest.di.newsApiModule
import com.example.nyttest.di.useCaseModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication  : Application() {

    private val modules = listOf(appModule, useCaseModules, newsApiModule, dbRepoModules)


    private fun setupKoin(applicationContext: Application) {
        startKoin {
            androidContext(applicationContext)
            modules(modules)
        }
    }

    override fun onCreate() {
        super.onCreate()
        setupKoin(this)
    }

}