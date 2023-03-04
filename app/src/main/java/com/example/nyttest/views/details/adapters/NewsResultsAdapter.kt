package com.example.nyttest.views.details.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nyttest.network.model.Results
import com.example.nyttest.views.details.listeners.ShowArticlesListener
import com.example.nyttest.views.details.viewholders.NewsResultsViewHolder

class NewsResultsAdapter(private val listenerArticle: ShowArticlesListener,/* var context: Context*/) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val articlesList: MutableList<Results> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        return NewsResultsViewHolder.inflate(parent.context, parent, listenerArticle)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NewsResultsViewHolder -> {
                val newsArticle = articlesList[position]

                holder.bind(newsArticle)
            }
        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }

    fun setData(list: MutableList<Results>) {
        var oldSize = itemCount
        list.forEach {
            articlesList.add(it)
        }
        notifyItemRangeInserted(oldSize, itemCount)
    }

    fun clearList() {
        articlesList.clear()
        notifyDataSetChanged()
    }
}

