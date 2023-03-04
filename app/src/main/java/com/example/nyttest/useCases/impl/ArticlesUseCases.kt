package com.example.nyttest.useCases.impl

import android.util.Log
import com.example.nyttest.network.model.ArticlesInfo
import com.example.nyttest.network.model.Results
import com.example.nyttest.useCases.NetworkUseCase
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArticlesUseCases(private val networkUseCase: NetworkUseCase) {

    private  var index = 0
    var LIMIT = listOf("1", "7", "30")
    private var currentPage = LIMIT[index]

    private var isLoadingData = false
    private var isLoadingFooter = false

    fun load(type: String,apiKey: String, listener: OnLoadListener) {

        if (index > 2){
            Log.d("Pagination State .... ","No More Pagination ")
        }else {
            if (isLoadingData) {
                listener.loading()
                return
            }

            Log.d("Pagination Index .... ", "${index.toString()}" )

            isLoadingData = true
            currentPage = LIMIT[index]
   /*     if (index > 1) {
            isLoadingData = false
            isLoadingFooter = false
            Log.d("LazyColumnListState ", "No More Pagination ")

        } else {
            if (isLoadingData) {
                listener.loading()
                return
            }
            if (isLoadingFooter) {
                listener.footerLoading()
            }


            currentPage = LIMIT[index]

            if (currentPage == LIMIT[0]) {
                isLoadingData = true
            } else {
                isLoadingFooter = true
            }

*/
            networkUseCase.callBackArticlesData(type,currentPage, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<ArticlesInfo> {
                    override fun onSubscribe(d: Disposable) {
                        listener.loading()
                    }

                    override fun onSuccess(response: ArticlesInfo) {

                        if(response.results.isNotEmpty()) {
                            index++
                        }
                        isLoadingData = false
                        listener.loaded(response.results)

                    }

                    override fun onError(ex: Throwable) {
                        isLoadingData = false
                        listener.error(ex)
                    }
                })
        }


    }

    /**
     * Reset current pagination to 0
     */
    fun refresh() {
        currentPage = LIMIT[0]
    }

    interface OnLoadListener {
        fun loading()
        fun loaded(data: MutableList<Results>)
        fun error(ex: Throwable)
    }

}