package com.example.nyttest.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nyttest.views.details.listeners.ShowArticlesListener

class ListLoadMoreCallback(private val listener: ShowArticlesListener)
    : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            recyclerView.layoutManager?.let {
                val visibleItemCount = it.childCount
                val totalItemCount = it.itemCount
                // grid also extends from linearLayoutManager
                val pastVisibleItems = (it as LinearLayoutManager).findFirstVisibleItemPosition()

                if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                    listener.loadMore(recyclerView)
                }
            }
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }
}