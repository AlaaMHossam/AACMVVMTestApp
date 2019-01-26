package com.lifetimecode.aacmvvmtestapp.data.repositories

import androidx.lifecycle.LiveData
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.Webservice
import com.lifetimecode.aacmvvmtestapp.data.datasources.network.networkcalls.FlightsNetworkCall
import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlightsRepository

@Inject
constructor(private val webservice: Webservice) {

    fun getFlightNetwork(): LiveData<FlightsData> {
        return FlightsNetworkCall(webservice).getFlights()
    }
}