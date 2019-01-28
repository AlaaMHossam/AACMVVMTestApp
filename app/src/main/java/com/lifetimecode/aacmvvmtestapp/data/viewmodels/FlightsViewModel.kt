package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class FlightsViewModel
@Inject
constructor(private var flightsRepository: FlightsRepository) : ViewModel() {

    var flightsLiveData: MutableLiveData<FlightsData> = MutableLiveData()

    //  fun getFlightsNetwork(): MutableLiveData<FlightsData> = flightsRepository.getFlightNetwork()

    //  fun getFlightsNetworkUpdateDB(): MutableLiveData<FlightsData> = flightsRepository.getFlightNetworkUpdateDB()

    fun getFlights() = CoroutineScope(Dispatchers.IO).launch {
        flightsRepository.flights1()
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                flightsLiveData.value = flightsRepository.flights1()
            }
        }
    }

    fun getFlightsUpdateDB() {


    }
}