package com.example.nyttest.views.details.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nyttest.R
import com.example.nyttest.databinding.ItemNewsBinding
import com.example.nyttest.network.model.Results
import com.example.nyttest.views.details.listeners.ShowArticlesCallBack
import com.example.nyttest.views.details.listeners.ShowArticlesListener
import org.koin.core.component.KoinComponent

class NewsResultsViewHolder(
    private val listener: ShowArticlesListener,
    private val view: ItemNewsBinding
) : RecyclerView.ViewHolder(view.root), View.OnClickListener, KoinComponent {


    private var data: Results? = null

    init {
        view.viewItem.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        data.let {
            when (v) {
                view.articleImage -> {
                    listener.onItemClicked(it!!)
                }
            }
        }
    }

    fun bind(data: Results) {
        this.data = data

        if (data.media.size > 0) {
            if (!data.media[0].`media-metadata`[0].url.isNullOrEmpty()) {
                Glide.with(view.root.context)
                    .load(data.media[0].`media-metadata`[0].url)
                    .into(view.articleImage)
            }
        } else {
            Glide.with(view.root.context)
                .load(R.drawable.placeholder_image)
                .into(view.articleImage)
        }

        view.articleTitle.text = data.title.toString()
        view.articleTime.text = data.published_date.toString()
    }


    companion object {
        fun inflate(
            context: Context,
            viewGroup: ViewGroup,
            listener: ShowArticlesListener
        ): NewsResultsViewHolder {
            val inflater = LayoutInflater.from(context)
             var _binding: ItemNewsBinding? = null
            _binding = ItemNewsBinding.inflate(inflater,viewGroup,false )
            return NewsResultsViewHolder(listener,_binding)
        }
    }
}