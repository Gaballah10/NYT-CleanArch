package com.example.nyttest.views.details.observers

import androidx.lifecycle.Observer
import com.example.nyttest.useCases.NetworkConnectionUseCase
import com.example.nyttest.views.details.DetailsView
import com.example.nyttest.views.main.MainView

class NetworkObserver(
    private val rootView: DetailsView
) : Observer<NetworkConnectionUseCase.NetworkStates> {


    override fun onChanged(action : NetworkConnectionUseCase.NetworkStates?) {
        when (action) {
            is NetworkConnectionUseCase.NetworkStates.Connected -> {
                val state = DetailsView.State.Connected
                rootView.changeNetworkState(state)
            }
            is NetworkConnectionUseCase.NetworkStates.NoNetwork -> {
                val state = DetailsView.State.NoNetwork
                rootView.changeNetworkState(state)
            }
        }
    }
}