package com.example.nyttest.views.main

import androidx.appcompat.app.AppCompatActivity
import com.example.nyttest.util.BaseActivityView


interface MainView: BaseActivityView {

    fun setupViews(activity: AppCompatActivity)
}