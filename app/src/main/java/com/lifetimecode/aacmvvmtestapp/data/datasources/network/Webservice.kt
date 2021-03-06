package com.lifetimecode.aacmvvmtestapp.data.datasources.network

import com.lifetimecode.aacmvvmtestapp.data.models.flightsmodel.FlightsData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface Webservice {

    @GET("v3/en/flights.php")
    fun getFlightsAsync(): Deferred<FlightsData>
}