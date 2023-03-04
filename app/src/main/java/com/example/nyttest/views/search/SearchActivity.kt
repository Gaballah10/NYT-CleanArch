package com.example.nyttest.views.search

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import com.example.nyttest.R
import com.example.nyttest.util.BaseActivity
import com.example.nyttest.views.details.DetailsActivity
import org.koin.android.ext.android.inject

class SearchActivity : BaseActivity() {
    private val view: SearchView by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = getResources().getColor(R.color.colorPrimary)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            window.addFlags(flags)
        }

        view.inflate(layoutInflater, null)?.let { layout ->
            setContentView(layout)
        }
        view.setupViews(this)
    }

    companion object {
        fun launch(context: Context, textSearch: String?) {
            val intent = Intent().setClass(context, DetailsActivity::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("search_text", textSearch)
            context.startActivity(intent)
        }
    }
}