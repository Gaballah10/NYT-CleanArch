package com.example.nyttest.testUtils

import android.content.Context
import com.example.nyttest.db.dao.ArticlesDataDao
import io.mockk.mockk
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

class Injector : KoinTest {

    private val mockContext: Context = mockk(relaxed = true)


    fun start() {
        startKoin {
            androidContext(mockContext)
            modules(
                listOf(
                    module {
                       factory { mockk<ArticlesDataDao>(relaxed = true) }

                    }
                )
            )
        }
    }

    fun stop() {
        stopKoin()
    }
}