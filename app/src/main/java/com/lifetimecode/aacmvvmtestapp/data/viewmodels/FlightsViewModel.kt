package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class FlightsViewModel
@Inject
constructor(private var flightsRepository: FlightsRepository) : ViewModel() {



  //  fun getFlightsNetwork(): MutableLiveData<FlightsData> = flightsRepository.getFlightNetwork()

  //  fun getFlightsNetworkUpdateDB(): MutableLiveData<FlightsData> = flightsRepository.getFlightNetworkUpdateDB()

    private suspend fun getFlightsNetwork() = flightsRepository.flights()

    fun start() {

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch { getFlightsNetwork()
            Log.d("FlightsViewModel", "start : ")
        }
    }
}