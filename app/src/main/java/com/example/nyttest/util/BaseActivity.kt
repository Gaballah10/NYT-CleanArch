package com.example.nyttest.util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            super.attachBaseContext(LocaleManager.applyLanguage(it))
        }
    }
}