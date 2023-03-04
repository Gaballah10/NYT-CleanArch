package com.example.nyttest.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.nyttest.databinding.ActivityMainBinding
import com.example.nyttest.views.search.SearchActivity

class MainViewImpl : MainView {

    private var rootView: View? = null
    private var _binding:ActivityMainBinding ? = null
    private val binding get() = _binding!!

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): View? {
       // rootView = inflater.inflate(R.layout.activity_main, container, false)
        _binding = ActivityMainBinding.inflate(inflater, container, false)
        rootView = binding.root
        return rootView
    }

    override fun setupViews(activity: AppCompatActivity) {
        rootView?.let { view ->

            binding.searchTab.setOnClickListener {
                lunchSearch()
            }
            binding.mostViewedTab.setOnClickListener {
                lunchDetails("viewed")
            }
            binding.mostSharedTab.setOnClickListener {
                lunchDetails("shared")
            }
            binding.mostEmailedTab.setOnClickListener {
                lunchDetails("emailed")
            }
        }
    }

    private fun lunchDetails(msg: String) {
        rootView.let {
            MainActivity.launchDetails(it!!.context, msg)
        }
    }

    private fun lunchSearch() {
        rootView.let {
            MainActivity.launchSearch(it!!.context)
        }
    }
}