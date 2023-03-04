package com.example.nyttest.details

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.nyttest.db.dao.ArticlesDataDao
import com.example.nyttest.testUtils.InstantTaskExecutorExtension
import com.example.nytarticlestask.testUtils.RxImmediateSchedulerRule
import com.example.nyttest.testUtils.TestUtils
import com.example.nyttest.testUtils.setProperty
import com.example.nyttest.useCases.NetworkConnectionUseCase
import com.example.nyttest.useCases.impl.ArticlesUseCases
import com.example.nyttest.views.details.ArticlesAction
import com.example.nyttest.views.details.DetailsVM
import io.mockk.CapturingSlot
import io.mockk.clearAllMocks
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import io.reactivex.Observable
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS


@TestInstance(PER_CLASS)
class DetailsVMTest {

    lateinit var instance: DetailsVM
    val ruleRx = RxImmediateSchedulerRule()
    val mockNetworkConnectionUseCase: NetworkConnectionUseCase = mockk(relaxed = true)
    val mockArticlesUseCases: ArticlesUseCases = mockk(relaxed = true)
    val mockArticlesDataDao: ArticlesDataDao = mockk(relaxed = true)
    val mockLifecycleOwner: LifecycleOwner = mockk(relaxed = true)
    val mockContext: Context = mockk(relaxed = true)
    val mockLiveDataCategory: MutableLiveData<ArticlesAction> = mockk(relaxed = true)


    @BeforeAll
    fun beforeGroup() {
        ruleRx.run()
        InstantTaskExecutorExtension.beforeEach()
        mockkStatic(Observable::class)
    }

    @AfterAll
    fun afterGroup() {
        ruleRx.reset()
        InstantTaskExecutorExtension.afterEach()
        unmockkAll()
    }

    @BeforeEach
    fun beforeEachTest() {


        instance = DetailsVM(
            mockNetworkConnectionUseCase,
            mockArticlesUseCases,
            mockArticlesDataDao
        )
        instance.setProperty("liveDataAction", mockLiveDataCategory)
    }

    @AfterEach
    fun afterEachTest() {
        clearAllMocks()
    }

    @Nested
    @DisplayName("refresh")
    inner class Refresh {
        @Test
        @DisplayName("refresh & load")
        fun test() {
            instance.refresh()
            verify { mockArticlesUseCases.refresh() }
        }
    }

    @Nested
    @DisplayName("loadData(api-key)")
    inner class LoadData {

        val key = "123"
        val type = "emailed"

        @Test
        @DisplayName("Then api-key = value")
        fun test() {

            val slotListener = CapturingSlot<ArticlesUseCases.OnLoadListener>()
            instance.loadData(type,key)
            verify { mockArticlesUseCases.load(type,key, capture(slotListener)) }
            slotListener.captured.loading()
            verify { mockLiveDataCategory.value = any<ArticlesAction.Loading>() }

        }
    }

    @Nested
    @DisplayName("updateNetworkState(NetworkStates)")
    inner class UpdateNetworkStateWithParameter {
        @Test
        @DisplayName("Then networkStateLiveData = state")
        fun test() {
            val mockState = mockk<NetworkConnectionUseCase.NetworkStates>(relaxed = true)
            val mockLiveData =
                mockk<MutableLiveData<NetworkConnectionUseCase.NetworkStates>>(relaxed = true)
            val mockObserver =
                mockk<Observer<NetworkConnectionUseCase.NetworkStates>>(relaxed = true)

             TestUtils.setProperty(instance, "networkStateLiveData", mockLiveData)

            mockLiveData.observeForever(mockObserver)
            instance.updateNetworkState(mockState)
            verify { mockLiveData.value = mockState }

            mockLiveData.removeObserver(mockObserver)
        }
    }

     @Nested
     @DisplayName("observeNetworkConnection(lifecycleOwner, observer)")
     inner class ObserveNetworkConnectionWithParameter {
         @Test
         @DisplayName("Then networkConnectionUseCase.observe(lifecycleOwner, observer)")
         fun test() {
             val mockObserver = mockk<Observer<NetworkConnectionUseCase.NetworkStates>>(relaxed = true)
             instance.observeNetworkConnection(mockLifecycleOwner, mockObserver)

             verify { mockNetworkConnectionUseCase.observe(mockLifecycleOwner, mockObserver) }
         }
     }

    @Nested
    @DisplayName("unregisterNetworkConnectionCallback(context)")
    inner class UnRegisterNetworkConnectionCallbackWithParameter {
        @Test
        @DisplayName("Then networkConnectionUseCase.onDestroy(context)")
        fun test() {
            instance.unregisterNetworkConnectionCallback(mockContext)

            verify { mockNetworkConnectionUseCase.onDestroy(mockContext) }
        }
    }


}