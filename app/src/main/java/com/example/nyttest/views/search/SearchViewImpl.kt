package com.example.nyttest.views.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nyttest.databinding.ActivitySearchBinding
import com.example.nyttest.util.BaseActivity

class SearchViewImpl : SearchView {

    private var rootView: View? = null
    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding!!

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): View? {
        _binding = ActivitySearchBinding.inflate(inflater, container, false)
        rootView = binding.root
        return rootView
    }

    override fun setupViews(activity: BaseActivity) {
        rootView.let {
            binding.btnSearch2.setOnClickListener {
                search()
            }
        }
    }

    private fun search() {
        rootView.let {
            SearchActivity.launch(it!!.context, binding.etSearch.text.toString())
        }
    }
}