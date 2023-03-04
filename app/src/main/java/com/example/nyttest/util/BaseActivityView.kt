package com.example.nyttest.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface BaseActivityView {

    fun inflate(inflater: LayoutInflater, container: ViewGroup?): View?

}
