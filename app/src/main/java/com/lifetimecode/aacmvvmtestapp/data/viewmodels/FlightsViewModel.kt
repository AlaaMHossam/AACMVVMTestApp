package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository

class FlightsViewModel : ViewModel {

    constructor()

    fun getFlights(flightsRepository: FlightsRepository): LiveData<FlightsData> {
        return flightsRepository.getFlightNetwork()
    }

}