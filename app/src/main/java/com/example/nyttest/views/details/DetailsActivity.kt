package com.example.nyttest.views.details

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.example.nyttest.BuildConfig
import com.example.nyttest.R
import com.example.nyttest.util.BaseActivity
import com.example.nyttest.views.details.observers.ArticlesActionObserver
import com.example.nyttest.views.details.observers.NetworkObserver
import org.koin.android.ext.android.inject

class DetailsActivity : BaseActivity() {

    private val view: DetailsViewImpl by inject()
    private val detailsVM: DetailsVM by inject()
    private var searchTextValue: String? = null

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

        searchTextValue = this.intent!!.getStringExtra("search_text").toString()
        view.setupViews(this)
        detailsVM.registerNetworkConnectionCallback(applicationContext)
        detailsVM.refresh()
        bindObservers()

    }

    private fun bindObservers() {

        detailsVM.loadData(searchTextValue.toString(), BuildConfig.api_key)

        detailsVM.observeNetworkConnection(
            this@DetailsActivity,
            Observer {
                detailsVM.updateNetworkState(it)
                detailsVM.getCachedData(it)
            }
        )


        detailsVM.observeNetworkState(
            this@DetailsActivity,
            NetworkObserver(view)
        )

        detailsVM.observeArticlesAction(
            this@DetailsActivity,
            ArticlesActionObserver(view)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        detailsVM.unregisterNetworkConnectionCallback(applicationContext)
    }
}