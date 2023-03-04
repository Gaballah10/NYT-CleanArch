package com.example.nyttest.testUtils

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor


object InstantTaskExecutorExtension {

    @SuppressLint("RestrictedApi")
    fun beforeEach() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }
        })
    }

    @SuppressLint("RestrictedApi")
    fun afterEach() {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}