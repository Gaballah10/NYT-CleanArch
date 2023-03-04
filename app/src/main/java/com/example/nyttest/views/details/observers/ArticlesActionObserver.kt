package com.example.nyttest.views.details.observers

import androidx.lifecycle.Observer
import com.example.nyttest.views.details.ArticlesAction
import com.example.nyttest.views.details.ResultStateView

class ArticlesActionObserver(
    private val rootView: ResultStateView
) : Observer<ArticlesAction> {

    override fun onChanged(action: ArticlesAction?) {

        action?.let {
            when (action) {
                is ArticlesAction.Loading -> {
                    val state = ResultStateView.State.Loading
                    rootView.changeState(state)
                }
                is ArticlesAction.NoData -> {
                    val state = ResultStateView.State.NoData
                    rootView.changeState(state)
                }
                is ArticlesAction.Result -> {
                    val state = ResultStateView.State.Results(action.list)
                    rootView.changeState(state)
                }
                is ArticlesAction.Error -> {
                    val state = ResultStateView.State.Error(action.exception)
                    rootView.changeState(state)
                }
            }
        }
    }
}