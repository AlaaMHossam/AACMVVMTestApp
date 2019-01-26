package com.lifetimecode.aacmvvmtestapp.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import com.lifetimecode.aacmvvmtestapp.data.repositories.FlightsRepository
import javax.inject.Inject

class FlightsViewModel

@Inject constructor(private var flightsRepository: FlightsRepository) : ViewModel() {

    fun getFlights(): LiveData<FlightsData> {
        return flightsRepository.getFlightNetwork()
    }
}