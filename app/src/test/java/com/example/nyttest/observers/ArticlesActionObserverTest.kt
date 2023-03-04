package com.example.nyttest.observers


import com.example.nyttest.views.details.ArticlesAction
import com.example.nyttest.views.details.ResultStateView
import com.example.nyttest.views.details.observers.ArticlesActionObserver
import io.mockk.Called
import io.mockk.CapturingSlot
import io.mockk.clearAllMocks
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ArticlesActionObserverTest {
    val mockRootView: ResultStateView = mockk(relaxed = true)

    lateinit var instance: ArticlesActionObserver

    @BeforeEach
    fun beforeEachTest() {
        instance = ArticlesActionObserver(mockRootView)
    }

    @AfterEach
    fun afterEachTest() {
        clearAllMocks()
    }

    @Nested
    @DisplayName("onChanged()")
    inner class OnChanged {

        @Nested
        @DisplayName("given action is LoadingData")
        inner class GivenActionIsLoadingData {
            @Test
            @DisplayName("then rootView changeState to Loading")
            fun test() {

                val fakeAction = ArticlesAction.Loading

                val slot = CapturingSlot<ResultStateView.State.Loading>()

                // test
                instance.onChanged(fakeAction)
                verify { mockRootView.changeState(capture(slot)) }
            }
        }
    }


    @Nested
    @DisplayName("given action is LoadedData")
    inner class GivenActionIsLoadedData {
        @Test
        @DisplayName("then rootView changeState to Loaded")
        fun test() {

            val fakeData =   mockk<MutableList<com.example.nyttest.network.model.Results>>(relaxed = true)
            val fakeAction = ArticlesAction.Result(fakeData)

            val slot = CapturingSlot<ResultStateView.State.Results>()

            // test
            instance.onChanged(fakeAction)
            verify { mockRootView.changeState(capture(slot)) }
            assertEquals(fakeData, slot.captured.list)
        }
    }

    @Nested
    @DisplayName("given action is Error")
    inner class GivenActionIsError {
        @Test
        @DisplayName("then rootView changeState to Error")
        fun test() {

            val fakeException = Throwable()
            val fakeAction = ArticlesAction.Error(fakeException)

            val slot = CapturingSlot<ResultStateView.State.Error>()

            // test
            instance.onChanged(fakeAction)
            verify { mockRootView.changeState(capture(slot)) }
            Assertions.assertEquals(fakeException, slot.captured.exception)
        }
    }

    @Nested
    @DisplayName("given other actions")
    inner class GivenOtherActions {
        @Test
        @DisplayName("then do nothing")
        fun test() {

            // test
            instance.onChanged(mockk(relaxed = true))
            verify { mockRootView wasNot Called }
        }
    }
}
