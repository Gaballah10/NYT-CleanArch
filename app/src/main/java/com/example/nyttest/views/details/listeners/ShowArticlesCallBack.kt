package com.example.nyttest.views.details.listeners

import androidx.recyclerview.widget.RecyclerView
import com.example.nyttest.network.model.Results
import com.example.nyttest.util.OnListLoadMoreListener

class ShowArticlesCallBack:ShowArticlesListener {

    private var listener: ShowArticlesListener ?= null

    fun setListener(listener: ShowArticlesListener) {
        this.listener = listener
    }

    override fun onItemClicked(data: Results) {
        listener?.onItemClicked(data)
    }

    override fun loadMore(recyclerView: RecyclerView) {
        listener?.loadMore(recyclerView)
    }

}