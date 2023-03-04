package com.example.nyttest.views.details

interface NewsStateView {

    fun changeState(state: State)

    sealed class State {
        object Loading : State()
        object NoData : State()
        class Results(val list: MutableList<com.example.nyttest.network.model.Results>) : State()
        class Error(val exception: Throwable) : State()
    }

}