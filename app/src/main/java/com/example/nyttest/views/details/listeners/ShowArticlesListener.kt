package com.example.nyttest.views.details.listeners

import androidx.recyclerview.widget.RecyclerView
import com.example.nyttest.network.model.Results
import com.example.nyttest.util.OnListLoadMoreListener

interface ShowArticlesListener {
    fun onItemClicked(data: Results)
    fun loadMore(recyclerView: RecyclerView)
}
