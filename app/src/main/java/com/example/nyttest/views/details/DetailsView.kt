package com.example.nyttest.views.details

import androidx.appcompat.app.AppCompatActivity
import com.example.nyttest.util.BaseActivityView
import com.example.nyttest.views.details.listeners.ShowArticlesListener

interface DetailsView : BaseActivityView ,ResultStateView  {

    fun dispose()
    fun setupViews(activity: AppCompatActivity)
    fun setArticlesListener(listener: ShowArticlesListener)

    fun changeNetworkState(state: State)
    sealed class State {
        object Connected : State()
        object NoNetwork : State()
    }
}