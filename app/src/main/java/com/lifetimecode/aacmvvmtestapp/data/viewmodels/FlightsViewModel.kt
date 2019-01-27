package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.Arrival
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository
import javax.inject.Inject

class FlightsViewModel

@Inject
constructor(private var flightsRepository: FlightsRepository) : ViewModel() {

    fun getFlightsNetwork(): LiveData<FlightsData> = flightsRepository.getFlightNetwork()

    fun getArrivalDB(): LiveData<List<Arrival>> = flightsRepository.getArrivalDB()

    fun saveArrivalDB() = flightsRepository.saveArrivalDB()
}