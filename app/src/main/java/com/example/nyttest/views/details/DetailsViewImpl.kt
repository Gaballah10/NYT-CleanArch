package com.example.nyttest.views.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nyttest.databinding.ActivityDetailsBinding
import com.example.nyttest.network.model.Results
import com.example.nyttest.util.ListLoadMoreCallback
import com.example.nyttest.views.details.adapters.NewsResultsAdapter
import com.example.nyttest.views.details.listeners.ShowArticlesCallBack
import com.example.nyttest.views.details.listeners.ShowArticlesListener

class DetailsViewImpl : DetailsView {

    private var rootView: View? = null
    private var _binding: ActivityDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewCallback = ShowArticlesCallBack()
    private   var adapter = NewsResultsAdapter(viewCallback)
    private var searchTextValue: String? = null
    var searchedData: MutableList<Results> = ArrayList()

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): View? {
        _binding = ActivityDetailsBinding.inflate(inflater, container, false)
        rootView = binding.root
        setupRecyclerView()
        return rootView
    }

    override fun setArticlesListener(listener: ShowArticlesListener) {
        viewCallback.setListener(listener)
    }

    override fun setupViews(activity: AppCompatActivity) {
        rootView?.let { view ->
            searchTextValue = activity.intent!!.getStringExtra("search_text").toString()
        }
    }


    override fun changeState(state: ResultStateView.State) {
        when (state) {
            is ResultStateView.State.NoData -> {
            }
            is ResultStateView.State.Loading -> {
                loadingView()
            }
            is ResultStateView.State.Results -> {
                loadData(state.list)
            }
            is ResultStateView.State.Error -> {
                errorView(state.exception)
            }
        }
    }

    override fun changeNetworkState(state: DetailsView.State) {
        rootView.let {
            when (state) {
                is DetailsView.State.Connected -> {
                    binding.noInternetMessage.visibility = View.GONE
                }
                is DetailsView.State.NoNetwork -> {
                    binding.noInternetMessage.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRecyclerView() {
        rootView?.let {

            //adapter = NewsResultsAdapter(viewCallback, it.context)
            binding.recylerNews.layoutManager = LinearLayoutManager(it!!.rootView.context)
            binding.recylerNews.adapter = adapter
            binding.recylerNews.addOnScrollListener(ListLoadMoreCallback(viewCallback))
            adapter.clearList()
        }
    }

    private fun loadData(list: MutableList<Results>) {
        rootView.let {

            binding.progressItem.visibility = View.GONE
            binding.recylerNews.visibility = View.VISIBLE

            if (searchTextValue != null &&
                searchTextValue != "viewed" && searchTextValue != "shared"
                        && searchTextValue != "emailed" ){
                filterData(searchTextValue.toString(), searchedData, list)
                adapter.clearList()
                if (searchedData.isEmpty()){
                    binding.noDataTv.visibility = View.VISIBLE
                }
                adapter.setData(searchedData)
                adapter.notifyDataSetChanged()
            }
            if (list.size > 0 &&  (searchTextValue == "viewed" || searchTextValue == "shared"
                        || searchTextValue == "emailed") ){
                adapter.setData(list)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun loadingView() {
        rootView.let {
            binding.progressItem.visibility = View.VISIBLE
            binding.recylerNews.visibility = View.GONE
        }
    }

    private fun errorView(ex: Throwable) {
    }

    override fun dispose() {
        rootView?.let {
            binding.recylerNews.adapter = null
        }
        rootView = null
    }

    fun filterData(value: String, filterData: MutableList<Results>, allData: MutableList<Results>) {
        filterData.clear()
        for (i in 0 until allData.size) {
            if (allData[i].title!!.contains(value)) {
                filterData.add(allData[i])
            }
        }
    }
}