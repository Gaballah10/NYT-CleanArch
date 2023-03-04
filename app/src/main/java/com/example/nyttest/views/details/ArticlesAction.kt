package com.example.nyttest.views.details

import com.example.nyttest.network.model.Results

sealed class ArticlesAction {

    object Loading : ArticlesAction()
    object NoData : ArticlesAction()
    class Result(val list: MutableList<Results>) : ArticlesAction()
    class Error(val exception: Throwable) : ArticlesAction()

}