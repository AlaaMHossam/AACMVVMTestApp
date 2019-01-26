package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository
import javax.inject.Inject

class FlightsViewModel : ViewModel {

    constructor()

    lateinit var flightsRepository: FlightsRepository

    @Inject
    constructor(flightsRepository: FlightsRepository){
        this.flightsRepository = flightsRepository
    }

    fun getFlights(): LiveData<FlightsData> {
        return flightsRepository.getFlightNetwork()
    }
}