package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository
import javax.inject.Inject

class FlightsViewModel

@Inject
constructor(private var flightsRepository: FlightsRepository) : ViewModel() {

    fun getFlightsNetwork(): MutableLiveData<FlightsData> = flightsRepository.getFlightNetwork()

    fun getFlightsNetworkUpdateDB(): MutableLiveData<FlightsData> = flightsRepository.getFlightNetworkUpdateDB()

    fun getArrivalDB(): LiveData<List<Arrival>>? {

        Log.d("FlightsViewModel", "getArrivalDB : ${flightsRepository.getdb().value}")
        return flightsRepository.getdb()
    }

//    fun saveArrivalDB() = flightsRepository.saveArrivalDB()
}