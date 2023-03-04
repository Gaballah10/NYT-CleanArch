package com.example.nyttest.di

import com.example.nyttest.db.database.AppDatabase
import com.example.nyttest.network.NewsNetwork
import com.example.nyttest.useCases.NetworkConnectionUseCase
import com.example.nyttest.useCases.NetworkUseCase
import com.example.nyttest.useCases.impl.ArticlesUseCases
import com.example.nyttest.useCases.impl.NetworkConnectionUseCaseImpl
import com.example.nyttest.useCases.impl.NetworkUseCaseImpl
import com.example.nyttest.views.details.DetailsVM
import com.example.nyttest.views.details.DetailsViewImpl
import com.example.nyttest.views.main.MainView
import com.example.nyttest.views.main.MainViewImpl
import com.example.nyttest.views.search.SearchView
import com.example.nyttest.views.search.SearchViewImpl
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    viewModel { DetailsVM(get(),get(),get()) }
}

@JvmField
val useCaseModules: Module = module {
    factory { NetworkUseCaseImpl(get()) as NetworkUseCase }
    factory<MainView> { MainViewImpl() }
    factory<SearchView> { SearchViewImpl() }
    factory { DetailsViewImpl() }
    factory<NetworkUseCase> { NetworkUseCaseImpl(get()) }
    factory<NetworkConnectionUseCase> { NetworkConnectionUseCaseImpl() }
    factory { ArticlesUseCases(get()) }
}

@JvmField
val newsApiModule = module {
    single {
        NewsNetwork.getArticleApi(
            interceptors = listOf(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ),
        )
    }
}
@JvmField
val dbRepoModules: Module = module {
    factory { AppDatabase.getAppDatabase(get()).articlesDataDao() }
}