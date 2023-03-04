package com.example.nyttest.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nyttest.views.details.listeners.ShowArticlesListener
import io.mockk.Called
import io.mockk.clearAllMocks
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
 import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ListLoadMoreCallbackTest {

    val mockListener: ShowArticlesListener = mockk(relaxed = true)

    lateinit var instance: ListLoadMoreCallback

    @BeforeEach
    fun beforeEachTest() {
        instance = ListLoadMoreCallback(mockListener)
    }

    @AfterEach
    fun afterEachTest() {
        clearAllMocks()
    }

    @Nested
    @DisplayName("onScrolled()")
    inner class OnScrolled {

        val mockRecyclerView: RecyclerView = mockk(relaxed = true)
        val mockLayoutManager: LinearLayoutManager = mockk(relaxed = true)

        @BeforeEach
        fun beforeEachTest() {
            every { mockRecyclerView.layoutManager } returns mockLayoutManager
        }

        @AfterEach
        fun afterEachTest() {
            clearMocks(mockRecyclerView, mockLayoutManager)
        }

        @Nested
        @DisplayName("given dy is more than 0")
        inner class GivenDYIsMoreThanZero {

            @Nested
            @DisplayName("given recyclerView.layoutManager not null")
            inner class GivenRecyclerViewLayoutManagerNotNull {

                @Nested
                @DisplayName("given is scroll to last")
                inner class GivenIsScrollToLast {
                    @Test
                    @DisplayName("then listener loadMore")
                    fun test() {

                        val itemCount = 10
                        val childCount = 5
                        val pastItems = 5

                        every { mockLayoutManager.childCount } returns childCount
                        every { mockLayoutManager.itemCount } returns itemCount
                        every { mockLayoutManager.findFirstVisibleItemPosition() } returns pastItems

                        // test
                        instance.onScrolled(mockRecyclerView, 0, 100)
                        verify { mockListener.loadMore(mockRecyclerView) }
                    }
                }

                @Nested
                @DisplayName("given is scroll not till last")
                inner class GivenIsScrollNotTillLast {
                    @Test
                    @DisplayName("then do nothing")
                    fun test() {

                        val itemCount = 10
                        val childCount = 4
                        val pastItems = 2

                        every { mockLayoutManager.childCount } returns childCount
                        every { mockLayoutManager.itemCount } returns itemCount
                        every { mockLayoutManager.findFirstVisibleItemPosition() } returns pastItems

                        // test
                        instance.onScrolled(mockRecyclerView, 0, 100)
                        verify { mockListener wasNot Called }
                    }
                }
            }

            @Nested
            @DisplayName("given recyclerView.layoutManager is null")
            inner class GivenRecyclerViewLayoutManagerIsNull {
                @Test
                @DisplayName("then do nothing")
                fun test() {

                    every { mockRecyclerView.layoutManager } returns null

                    // test
                    instance.onScrolled(mockRecyclerView, 0, 100)
                    verify { mockListener wasNot Called }
                }
            }
        }

        @Nested
        @DisplayName("given dy is equal or less than 0")
        inner class GivenDYIsEqualOrLessThanZero {
            @Test
            @DisplayName("then do nothing")
            fun test() {

                // test
                instance.onScrolled(mockRecyclerView, 0, 0)
                verify { mockListener wasNot Called }
            }
        }
    }

    @Nested
    @DisplayName("onScrollStateChanged()")
    inner class OnScrollStateChanged {
        @Test
        @DisplayName("then do nothing")
        fun test() {

            instance.onScrollStateChanged(mockk(relaxed = true), 0)
            // do nothing
        }
    }
}