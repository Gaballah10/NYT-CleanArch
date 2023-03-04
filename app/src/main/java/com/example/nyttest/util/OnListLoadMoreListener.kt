package com.example.nyttest.util

import androidx.recyclerview.widget.RecyclerView

interface OnListLoadMoreListener {
    fun loadMore(recyclerView: RecyclerView)
}